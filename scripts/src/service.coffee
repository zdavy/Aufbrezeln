TTT.Service =
  postNewGame: (rules, fn) ->
    @_post("/game/new-game", rules, fn)

  postMove: (rules, index, fn) ->
    rules["gameBoard"] = JSON.stringify(rules["gameBoard"])
    rules["gameMove"]  = index
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
