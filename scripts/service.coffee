TTT.Service =
  postNewGame: (rules, fn) ->
    console.log("postNewGame")
    @_post("/game/new-game", rules, fn)

  postMove: (rules, index, fn) ->
    console.log("postMove")
    rules["gameMove"] = index
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
