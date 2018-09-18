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

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://104.248.24.36/codenjoy-contest/board/player/ol.leichenko@gmail.com?code=2142052770436339034",
                new YourSolver(new RandomDice()),
                new Board());

    }

}
