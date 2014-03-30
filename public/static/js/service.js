(function() {
  TTT.Service = {
    postNewGame: function(rules, fn) {
      return this._post("/game/new-game", rules, fn);
    },
    postMove: function(rules, index, fn) {
      rules["gameMove"] = index;
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
