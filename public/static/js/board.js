(function() {
  var TTT,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  TTT = window.TTT = {};

  TTT.Board = (function() {
    function Board() {
      this.assignGameRules = __bind(this.assignGameRules, this);
      this.startNewGame = __bind(this.startNewGame, this);
      this.spaces = $(".boardPiece");
      this.view = new TTT.BoardView;
    }

    Board.prototype.init = function() {
      return this.bindNewGame();
    };

    Board.prototype.bindNewGame = function() {
      return $("[data-id='newGame']").on("click", (function(_this) {
        return function() {
          _this.view.reset();
          _this.assignGameRules();
          return _this.startNewGame();
        };
      })(this));
    };

    Board.prototype.startNewGame = function() {
      TTT.Service.postNewGame(this.gameRules, (function(_this) {
        return function(newGame) {
          return _this.updateGame(newGame);
        };
      })(this));
      return this.bindGameBoard();
    };

    Board.prototype.updateGame = function(gameData) {
      this.updateBoard(gameData["board"]);
      this.assignTurn(gameData["turn"]);
      return this.view.sync(gameData);
    };

    Board.prototype.getWinner = function(results) {
      if (results.winner === "draw") {
        return alert("The game is a draw, nobody wins");
      } else {
        return alert("The winner is " + results.winner + ", congrats!");
      }
    };

    Board.prototype.bindGameBoard = function() {
      return this.spaces.not(".cross, .circle").on("click", (function(_this) {
        return function(event) {
          var index;
          _this.spaces.unbind();
          index = $(event.target).data("index-id");
          return TTT.Service.postMove(_this.gameRules, index, function(response) {
            _this.updateGame(response);
            return _this.checkGameStatus(response);
          });
        };
      })(this));
    };

    Board.prototype.checkGameStatus = function(gameData) {
      if (gameData["game-over"]) {
        return this.getWinner(gameData);
      } else {
        return this.bindGameBoard();
      }
    };

    Board.prototype.updateBoard = function(board) {
      return this.gameRules.gameBoard = board;
    };

    Board.prototype.assignTurn = function(turn) {
      if (turn === "player1") {
        return this.gameRules.gameTurn = "first-player";
      } else {
        return this.gameRules.gameTurn = "second-player";
      }
    };

    Board.prototype.assignGameRules = function() {
      return this.gameRules = {
        gameMode: $("[data-id='gameMode']").val(),
        gameTurn: $("[data-id='gameTurn']").val(),
        gameDifficulty: $("[data-id='gameDifficulty']").val()
      };
    };

    return Board;

  })();

  TTT.BoardView = (function() {
    function BoardView() {}

    BoardView.prototype.reset = function() {
      var index, _i, _results;
      _results = [];
      for (index = _i = 0; _i <= 8; index = ++_i) {
        _results.push(this.removeClass(index));
      }
      return _results;
    };

    BoardView.prototype.sync = function(gameData) {
      this.reset();
      this.displayBoard(gameData["board"]);
      return this.turn = gameData["turn"];
    };

    BoardView.prototype.displayBoard = function(board) {
      var index, _i, _results;
      _results = [];
      for (index = _i = 0; _i <= 8; index = ++_i) {
        _results.push(this.applyClass(board[index], index));
      }
      return _results;
    };

    BoardView.prototype.applyClass = function(marker, position) {
      if (marker === "x") {
        return this._applyClass("cross", position);
      } else if (marker === "o") {
        return this._applyClass("circle", position);
      }
    };

    BoardView.prototype._applyClass = function(marker, position) {
      return this.space(position).addClass(marker);
    };

    BoardView.prototype.removeClass = function(position) {
      this.space(position).removeClass("circle");
      return this.space(position).removeClass("cross");
    };

    BoardView.prototype.space = function(position) {
      return $("[data-index-id='" + position + "'");
    };

    return BoardView;

  })();

  window.TTT.Board = TTT.Board;

  window.TTT.BoardView = TTT.BoardView;

}).call(this);
