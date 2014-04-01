(function() {
  TTT.Service = {
    postNewGame: function(rules, fn) {
      return this._post("/game/new-game", rules, fn);
    },
    postMove: function(rules, index, fn) {
      var board;
      rules["gameMove"] = index;
      console.log(rules);
      board = rules["gameBoard"];
      rules["gameBoard"] = JSON.stringify(board);
      console.log(rules);
      return this._post("/game/make-move", rules, fn);
    },
    _post: function(url, data, callback) {
      return $.ajax({
        type: "POST",
        dataType: 'json',
        url: url,
        data: data,
        success: callback
      });
    }
  };

  window.TTT.Service = TTT.Service;

}).call(this);
