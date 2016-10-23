/**
 *
 */
public class Main{
	public static int turnsTaken = 0;
    /**
     * @param args the command line arguments
     */
	
    public static void main(String[] args) {
        // Starting node for the game tree,
        // Initial board is just constructor for Board with
        // no parameters: constructs an initial board
        GameTreeNode gameTree = new GameTreeNode(new Board());

        // Player will be player 1 (true, value = 1) at first
        boolean player = true;

        // Explore the tree with a maximum depth of 3 to build tree
        gameTree.expand( player, 3 );

        long startTime = System.currentTimeMillis();
        // Game loop
        while(!gameTree.BP.gameOver(player)){
            System.out.println(gameTree.BP);

            try {
            	if(turnsTaken % 2 == 0)
            	{
            		gameTree = gameTree.getBestMove(player);    // Move game tree down to next move
            		System.out.println("call regular");
            	}
            		
            	else
            	{
            		gameTree = gameTree.getBestMoveAlphaBeta(player);    // Move game tree down to next move
            		System.out.println("call beta");
            	}
            		
                
            } catch (EmptyChildrenException e) {
                e.printStackTrace();
            }
            ++turnsTaken;
            System.out.println("REecnt");
            player = !player;
        }
        
		
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		
        System.out.println(gameTree.BP);
        if (gameTree.BP.winner == 1){
            System.out.println("Player 1 has won!!");
        }
        if (gameTree.BP.winner == -1){
            System.out.println("Player 2 has won!!");
        }
        
        System.out.println("\n\nTurnsTaken   " + turnsTaken);
        int whiteTurns;
        int blackTurns;
        if(turnsTaken % 2 == 0)
        {
        	whiteTurns = turnsTaken/2;
        	blackTurns = turnsTaken/2;
        }
        else
        {
        	whiteTurns = turnsTaken/2+1;
        	blackTurns = turnsTaken/2;
        }
        
        System.out.println("Nodes white explored!   " + GameTreeNode.whiteNodesExplored);
        System.out.println("Nodes black explored!   " + GameTreeNode.blackNodesExplored);
        
        System.out.println("GAME played in " + totalTime + " ms");
        System.out.println("Time per turn  " + (double)(totalTime)/turnsTaken + " ms");
        
        
        System.out.println("Nodes expanded per turn   " + (GameTreeNode.whiteNodesExplored + GameTreeNode.blackNodesExplored)/turnsTaken);
        //System.out.println("Nodes Expanded white per turn  " + (double)(GameTreeNode.whiteNodesExplored)/whiteTurns + " ms");
        //System.out.println("Nodes expanded black per turn  " + (double)(GameTreeNode.blackNodesExplored)/blackTurns + " ms");
        
        //get the final board and count remaining pieces
        int[][] myBoard = gameTree.BP.board;
        int whiteRemain = 0;
        int blackRemain = 0;
        
        for(int i = 0; i < 8; i++)
        {
        	for(int m = 0; m < 8; m++)
        	{
        		if(myBoard[i][m] == 1)
        		{
        			whiteRemain++;
        		}
        		if(myBoard[i][m] == -1)
        			blackRemain++;
        	}
        }
        
        System.out.println("White had how many captured:  " + (16-whiteRemain));
        System.out.println("Black had how many captured:  " + (16-blackRemain));
        System.out.println(gameTree.BP);
    }
}
