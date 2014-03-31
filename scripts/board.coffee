TTT = window.TTT = {}

class TTT.Board
  constructor: ->
    @spaces = $(".boardPiece")
    @view = new TTT.BoardView
  init: ->
    @bindNewGame()

  bindNewGame: ->
    $("[data-id='newGame']").on "click", =>
      @view.reset()
      @assignGameRules()
      @startNewGame()

  startNewGame: =>
    console.log(TTT.Service.postNewGame @gameRules, (newGame) =>
      @updateGame(newGame))
    @bindGameBoard()

  updateGame: (gameData) ->
    console.log("nope")
    @updateBoard(gameData["board"])
    @assignTurn(gameData["turn"])
    @view.sync(gameData)

  getWinner: (results) ->
    if (results.winner == "draw")
      alert("The game is a draw, nobody wins")
    else
      alert("The winner is #{results.winner}, congrats!")

  bindGameBoard : ->
    @spaces.not(".cross, .circle").on "click", (event) =>
      @spaces.unbind()
      index = $(event.target).data("index-id")
      TTT.Service.postMove @gameRules, index, (response) =>
        @updateGame(response)
        @checkGameStatus(response)

  checkGameStatus: (gameData) ->
    if gameData["game-over"]
      @getWinner(gameData)
    else
      @bindGameBoard()

  updateBoard : (board) ->
    @gameRules.gameBoard = board

  assignTurn: (turn) ->
    if turn == "player1"
      @gameRules.gameTurn = "first-player"
    else
      @gameRules.gameTurn = "second-player"

  assignGameRules: =>
    @gameRules = {
      gameMode: $("[data-id='gameMode']").val()
      gameTurn: $("[data-id='gameTurn']").val()
      gameDifficulty: $("[data-id='gameDifficulty']").val()
    }

class TTT.BoardView
  reset: ->
    for index in [0..8]
      @removeClass(index)

  sync: (gameData) ->
    @reset()
    @displayBoard(gameData["board"])
    @turn = gameData["turn"]

  displayBoard: (board) ->
    for index in [0..8]
      @applyClass(board[index], index)

  applyClass: (marker, position) ->
    if marker == "x"
      @_applyClass("cross", position)
    else if marker == "o"
      @_applyClass("circle", position)

  _applyClass: (marker, position) ->
    @space(position).addClass(marker)

  removeClass: (position) ->
    @space(position).removeClass("circle")
    @space(position).removeClass("cross")

  space: (position) ->
    $("[data-index-id='#{position}'")

window.TTT.Board = TTT.Board
window.TTT.BoardView = TTT.BoardView
