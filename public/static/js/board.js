(function() {
  var TTT, _delay,
    __bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; },
    __slice = [].slice;

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
      return TTT.Service.postNewGame(this.gameRules, (function(_this) {
        return function(newGame) {
          _this.updateGame(newGame);
          return _this.bindGameBoard();
        };
      })(this));
    };

    Board.prototype.updateGame = function(gameData) {
      this.updateBoard(gameData["board"]);
      this.assignTurn(gameData["turn"]);
      return this.view.sync(gameData);
    };

    Board.prototype.getWinner = function(results) {
      if (this.draw(results)) {
        return drawMessage();
      } else {
        return this.winMessage(results.winner);
      }
    };

    Board.prototype.draw = function(results) {
      return results.winner === "draw";
    };

    Board.prototype.drawMessage = function() {
      return alert("The game is a draw, nobody wins");
    };

    Board.prototype.winMessage = function(winner) {
      return _delay(function() {
        return alert("The winner is " + winner + ", congrats!");
      });
    };

    Board.prototype.bindGameBoard = function() {
      return this.spaces.not(".cross, .circle").on("click", (function(_this) {
        return function(event) {
          _this.spaces.unbind();
          return TTT.Service.postMove(_this.gameRules, _this.getIndex(event), function(response) {
            _this.updateGame(response);
            return _this.checkGameStatus(response);
          });
        };
      })(this));
    };

    Board.prototype.getIndex = function(event) {
      return $(event.target).data("index-id");
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
        return this.firstTurn();
      } else {
        return this.secondTurn();
      }
    };

    Board.prototype.firstTurn = function() {
      return this.gameRules.gameTurn = "first-player";
    };

    Board.prototype.secondTurn = function() {
      return this.gameRules.gameTurn = "second-player";
    };

    Board.prototype.assignGameRules = function() {
      return this.gameRules = {
        gameDifficulty: $("[data-id='gameDifficulty']").val(),
        gameMode: $("[data-id='gameMode']").val(),
        gameTurn: $("[data-id='gameTurn']").val()
      };
    };

    return Board;

  })();

  TTT.BoardView = (function() {
    function BoardView() {}

    BoardView.prototype.sync = function(gameData) {
      this.setBoards(gameData["board"]);
      this.setTurn(gameData["turn"]);
      return this.displayBoard();
    };

    BoardView.prototype.setTurn = function(turn) {
      return this.turn = turn;
    };

    BoardView.prototype.setBoards = function(board) {
      this.oldBoard = this.newBoard;
      return this.newBoard = board;
    };

    BoardView.prototype.displayBoard = function(board) {
      var index, _i, _results;
      this.reset();
      _results = [];
      for (index = _i = 0; _i <= 8; index = ++_i) {
        _results.push(this.applyClass(this.newBoard[index], index));
      }
      return _results;
    };

    BoardView.prototype.applyClass = function(marker, position) {
      if (marker === "x") {
        this.applyCross(position);
      }
      if (marker === "o") {
        if (this.played(position)) {
          return this.applyCircle(position);
        } else {
          return _delay((function(_this) {
            return function() {
              return _this.applyCircle(position);
            };
          })(this));
        }
      }
    };

    BoardView.prototype.played = function(position) {
      return this.oldBoard[position] === "o";
    };

    BoardView.prototype.applyCross = function(position) {
      return this._applyClass("cross", position);
    };

    BoardView.prototype.applyCircle = function(position) {
      return this._applyClass("circle", position);
    };

    BoardView.prototype._applyClass = function(marker, position) {
      return this.space(position).addClass(marker);
    };

    BoardView.prototype.reset = function() {
      var index, _i, _results;
      _results = [];
      for (index = _i = 0; _i <= 8; index = ++_i) {
        _results.push(this.removeClass(this.space(index)));
      }
      return _results;
    };

    BoardView.prototype.removeClass = function(position) {
      return position.removeClass("cross circle");
    };

    BoardView.prototype.space = function(position) {
      return $("[data-index-id='" + position + "'");
    };

    return BoardView;

  })();

  _delay = function() {
    var args, fn;
    fn = arguments[0], args = 2 <= arguments.length ? __slice.call(arguments, 1) : [];
    return setTimeout.apply(null, [fn, 100].concat(__slice.call(args)));
  };

  window.TTT.Board = TTT.Board;

  window.TTT.BoardView = TTT.BoardView;

}).call(this);
