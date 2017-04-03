package tic;

import java.util.ArrayList;
import java.util.List;

 
 

 

 public class AI {
	 final static int CPU_POWER =60000000;
	 
int comp;
int human;
 moves move;
int depth ;
int computationTime;
List<moves> possibleMoves;
public AI(int player,int size){
	 moves move = new moves();
	  this.comp = player;
	  if(comp ==1) human =0;
	  else human =1;
	 
	  possibleMoves = new ArrayList<moves>((size*size)+1);
	  for(int x =1;x<size+1;x++){
		  for(int y =1;y<size+1;y++){
			  moves tempMoves = new moves();
			  tempMoves.x =x;
			  tempMoves.y =y;
			  possibleMoves.add(tempMoves);
		  }
	  }
	 
}
public moves getMove(int difficulty, Board board){
	 
	computationTime=0;
	  depth =0;
	  if(difficulty ==1) return easyMove(board);
	  else if(difficulty ==2) return mediumMove(board);		
	  else if(difficulty == 3){ return hardMove2(board,comp,depth );}
	  else return hardMove2(board,comp,depth );
}
public moves easyMove(Board board){
	return  move;
}
public moves mediumMove(Board board){
	return move;
}
public moves hardMove(Board board,int player,int depth ){
	
	 
	 //   computationTime++;
		int win = board.checkWin2();
		 depth++;
		if(win == comp) { moves move = new moves(); move.score=10-depth ; return move;} 
		else if(win == human){ moves move = new moves(); move.score=depth-10; return move;} 
		else if(win == -1) {moves move = new moves(); move.score=0; return move;} //else if(computationTime>CPU_POWER){ moves move = new moves(); move.score=0; return move;} 
		 List<moves> moves= new ArrayList<moves>();
		  for(moves move:possibleMoves){
			    int x= move.x;
			    int y= move.y;
			 
				if(board.coorD[x][y]==Character.MIN_VALUE){
					 board.addCoorD( x,  y, player);
					 moves tempMove = new moves();
					 tempMove.x =x;
					 tempMove.y =y;
					 
					 if(player == comp){
						 
						tempMove.score = hardMove(board,human,depth ).score;
					 }
					 else {		 
							tempMove.score = hardMove(board,comp,depth ).score;
					 }
					 moves.add(tempMove);
					 board.coorD[x][y]=Character.MIN_VALUE;
			
				}
				
		  }
		int bestMove=0;
		 if(player == comp){
			 int bestScore = Integer.MIN_VALUE;
			// int bestScore = -10000000;
			 for(int i =0;i<moves.size();i++){
				  if(moves.get(i).score>bestScore){
				     bestScore = moves.get(i).score;
				     bestMove =i;
				  }
			 }
		 }else
		 {   int bestScore = Integer.MAX_VALUE;
			// int bestScore = 10000000;
			 for(int i =0;i<moves.size();i++){
				  if(moves.get(i).score<bestScore){
				     bestScore = moves.get(i).score;
				     bestMove =i;
				  }
	} 
			 
   }
		 //System.out.println(levels);
		 return moves.get(bestMove);
		 
}
public moves hardMove2(Board board,int player,int depth ){
 
	moves bestMove = new moves( ); 
	 if(player ==comp) bestMove.score = Integer.MIN_VALUE;
	 else bestMove.score = Integer.MAX_VALUE;
	// System.out.println(bestMove.score);
  //  computationTime++;
	int win = board.checkWin2();
	 depth++;
	if(win == comp) { moves move = new moves(); move.score=10-depth ; return move;} 
	else if(win == human){ moves move = new moves(); move.score=depth-10; return move;} 
	else if(win == -1) {moves move = new moves(); move.score=0; return move;} //else if(computationTime>CPU_POWER){ moves move = new moves(); move.score=0; return move;} 
	  
	  for(moves move:possibleMoves){
		    int x= move.x;
		    int y= move.y;
		 
			if(board.coorD[x][y]==Character.MIN_VALUE){
				 board.addCoorD( x,  y, player);
				 moves tempMove = new moves();
				 tempMove.x =x;
				 tempMove.y =y;
				 
				 if(player == comp){
					 tempMove.score = hardMove2(board,human,depth ).score;
				
					
                     
                 
				 }
				 else {		 
					 tempMove.score = hardMove2(board,human,depth ).score;
				 
					   
				 }
				 bestMove = getMax(tempMove ,bestMove,player );
				 board.coorD[x][y]=Character.MIN_VALUE;
		
			}
			
	  }
     
	 //System.out.println(levels);
	 return  bestMove;
	 
}
public moves getMax(moves arg1,moves arg2,int player){
	if(player == comp){
		 
	 if(arg1.score>arg2.score){    return arg1;}
	 else return arg2; 
	 }
	else{
		 if(arg1.score < arg2.score) return arg1;
		 else return arg2;
	}
}
}
