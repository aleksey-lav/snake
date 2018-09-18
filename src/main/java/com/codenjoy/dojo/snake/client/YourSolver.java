package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.RandomDice;

import java.security.DigestException;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        System.out.println(board.toString());

        String movePath = Direction.UP.toString();

        int appleY = board.getApples().get(0).getY();
        int appleX = board.getApples().get(0).getX();
        int headerX = board.getHead().getX();
        int headerY = board.getHead().getY();

        if(appleY < headerY){
//            movePath = Direction.DOWN.toString();
            if( appleX < headerX){
                movePath = Direction.LEFT.toString();
            }
            if(appleX > headerX){
                movePath = Direction.RIGHT.toString();
            }
        }
        if(appleY >= headerY){
            movePath = Direction.RIGHT.toString();
        }
        if((appleX == headerX) && (headerY >= appleY)){
            movePath = Direction.DOWN.toString();
        }
        if ((appleX == headerX) && (headerY <= appleY)){
            movePath = Direction.UP.toString();
        }


        return movePath;
    }


//    public class SnakeGame {
//        int[][] food;
//        int index;
//        int row, col;
//        int x, y;
//        int len;
//        LinkedList<int[]> queue;
//        /** Initialize your data structure here.
//         @param width - screen width
//         @param height - screen height
//         @param food - A list of food positions
//         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
//        public SnakeGame(int width, int height, int[][] food) {
//            this.food=food;
//            this.index=0;
//            this.x=0;
//            this.y=0;
//            this.row=height;
//            this.col=width;
//            this.queue= new LinkedList<int[]>();
//            this.queue.offer(new int[]{0, 0});
//        }
//
//        /** Moves the snake.
//         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
//         @return The game's score after the move. Return -1 if game over.
//         Game over when snake crosses the screen boundary or bites its body. */
//        public int move(String direction) {
//            switch(direction){
//                case "U":
//                    x--;
//                    break;
//                case "L":
//                    y--;
//                    break;
//                case "R":
//                    y++;
//                    break;
//                case "D":
//                    x++;
//                    break;
//            }
//
//            if(!isValid(x,y)){
//                return -1;
//            }
//
//            return process(x, y);
//        }
//
//        public boolean isValid(int x, int y){
//            if(x<0 || x>=row || y<0 || y>=col)
//                return false;
//
//            return true;
//        }
//
//        public int process(int x, int y){
//
//            if(index==food.length){
//                queue.poll();
//
//            }else if(food[index][0]==x && food[index][1]==y){
//                len++;
//                index++;
//            }else{
//                queue.poll();
//            }
//
//            for(int[] p: queue){
//                if(p[0]==x&&p[1]==y)
//                    return -1;
//            }
//
//            queue.offer(new int[]{x,y});
//
//            return len;
//        }
//    }













    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://104.248.24.36/codenjoy-contest/board/player/ol.leichenko@gmail.com?code=2142052770436339034",
                new YourSolver(new RandomDice()),
                new Board());

    }

}
