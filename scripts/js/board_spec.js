(function() {
  describe("Board", function() {
    var board, newGame, testData;
    board = null;
    testData = {
      test: "data"
    };
    newGame = function() {
      return $("[data-id='newGame']").click();
    };
    beforeEach(function() {
      setFixtures("<input data-id='gameMode' value='mode'/>\n<input data-id='gameTurn' value='turn'/>\n<input data-id='gameDifficulty' value='difficulty'/>\n<div class=\"boardPiece\" data-index-id='0'>\n<div class=\"boardPiece circle\" data-index-id='1'>\n<div class=\"boardPiece cross\" data-index-id='2'>\n<button data-id='newGame'/>");
      board = new TTT.Board;
      spyOn(board.view, "reset");
      spyOn(TTT.Service, "postNewGame").and.returnValue(testData);
      spyOn(TTT.Service, "postMove").and.returnValue(testData);
      return board.init();
    });
    describe("new game button -> click", function() {
      it("collects options and passes them to the new game service", function() {
        newGame();
        return expect(TTT.Service.postNewGame).toHaveBeenCalled();
      });
      return it("resets the board view", function() {
        newGame();
        return expect(board.view.reset).toHaveBeenCalled();
      });
    });
    return describe("Making a move", function() {
      it("passes the index from the div clicked to the postMove service", function() {
        newGame();
        $("[data-index-id='0']").click();
        return expect(TTT.Service.postMove).toHaveBeenCalled();
      });
      return it("doesn't accept clicks on already take spaces", function() {
        newGame();
        expect($("[data-index-id='1']")).not.toHandle("click");
        return expect($("[data-index-id='2']")).not.toHandle("click");
      });
    });
  });

}).call(this);
