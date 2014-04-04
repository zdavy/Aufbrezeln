TTT = window.TTT = {}

class TTT.Board
  constructor: ->
    @spaces = $(".boardPiece")
    @view = new TTT.BoardView

  init: -> @bindNewGame()

  bindNewGame: ->
    $("[data-id='newGame']").on "click", =>
      @view.reset()
      @assignGameRules()
      @startNewGame()

  startNewGame: =>
    TTT.Service.postNewGame @gameRules, (newGame) =>
      @updateGame(newGame)
      @bindGameBoard()

  updateGame: (gameData) ->
    @updateBoard(gameData["board"])
    @assignTurn(gameData["turn"])
    @view.sync(gameData)

  getWinner: (results) ->
    if @draw(results) then drawMessage() else @winMessage(results.winner)

  draw: (results) -> results.winner == "draw"
  drawMessage: -> alert("The game is a draw, nobody wins")
  winMessage: (winner) -> _delay -> alert("The winner is #{winner}, congrats!")

  bindGameBoard : ->
    @spaces.not(".cross, .circle").on "click", (event) =>
      @spaces.unbind()
      TTT.Service.postMove @gameRules, @getIndex(event), (response) =>
        @updateGame(response)
        @checkGameStatus(response)

  getIndex: (event) -> $(event.target).data("index-id")

  checkGameStatus: (gameData) ->
    if gameData["game-over"] then @getWinner(gameData) else @bindGameBoard()

  updateBoard : (board) -> @gameRules.gameBoard = board

  assignTurn: (turn) -> if turn == "player1" then @firstTurn() else @secondTurn()
  firstTurn: -> @gameRules.gameTurn = "first-player"
  secondTurn: -> @gameRules.gameTurn = "second-player"

  assignGameRules: =>
    @gameRules = {
      gameDifficulty: $("[data-id='gameDifficulty']").val()
      gameMode:       $("[data-id='gameMode']").val()
      gameTurn:       $("[data-id='gameTurn']").val()
    }

class TTT.BoardView
  sync: (gameData) ->
    @setBoards(gameData["board"])
    @setTurn(gameData["turn"])
    @displayBoard()

  setTurn: (turn) -> @turn = turn
  setBoards: (board) -> @oldBoard = @newBoard; @newBoard = board

  displayBoard: (board) ->
    @reset()
    @applyClass(@newBoard[index], index) for index in [0..8]

  applyClass: (marker, position) ->
    @applyCross(position) if marker == "x"
    if marker == "o"
      if @played(position) then @applyCircle(position)
      else _delay => @applyCircle(position)

  played: (position) -> @oldBoard[position] == "o"
  applyCross: (position) -> @_applyClass("cross", position)
  applyCircle: (position) -> @_applyClass("circle", position)
  _applyClass: (marker, position) -> @space(position).addClass(marker)

  reset: -> @removeClass(@space(index)) for index in [0..8]
  removeClass:  (position) -> position.removeClass("cross circle")

  space: (position) -> $("[data-index-id='#{position}'")

_delay = (fn, args...) ->
  setTimeout fn, 500, args...

window.TTT.Board = TTT.Board
window.TTT.BoardView = TTT.BoardView
