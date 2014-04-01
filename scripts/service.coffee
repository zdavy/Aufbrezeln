TTT.Service =
  postNewGame: (rules, fn) ->
    @_post("/game/new-game", rules, fn)

  postMove: (rules, index, fn) ->
    rules["gameMove"] = index
    console.log(rules)
    board = rules["gameBoard"]
    rules["gameBoard"] = JSON.stringify(board)
    console.log(rules)
    @_post("/game/make-move", rules, fn)

  _post: ( url, data, callback) ->
    $.ajax({
      type: "POST"
      dataType: 'json'
      url : url
      data: data
      success: callback
    })

window.TTT.Service = TTT.Service
