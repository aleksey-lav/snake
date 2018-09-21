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
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.RandomDice;

import java.util.List;

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

        int appleY = board.getApples().get(0).getY();
        int appleX = board.getApples().get(0).getX();
        int headerX = board.getHead().getX();
        int headerY = board.getHead().getY();

        String curDir = board.getSnakeDirection().toString();
        String path = Direction.RIGHT.toString();

        int divX = 0; //next step by X
        int divY = 0; //next step by Y

        int nextX;
        int nextY;

        if (headerX < appleX) {
            divX = 1;
        }
        if (headerX > appleX) {
            divX = -1;
        }
        if (headerY < appleY) {
            divY = 1;
        }
        if (headerY > appleY) {
            divY = -1;
        }

        /*nextX = board.getHead().getX() + divX;
        nextY = board.getHead().getY() + divY;*/

        //step right
        if (divX > 0) {
            if (ifPresentBarrier(board, board.getHead(), divX, 0)) {
                return Direction.RIGHT.toString();
            }
        }
        if (divY < 0) {
            if (ifPresentBarrier(board, board.getHead(), 0, divY)) {
                return Direction.DOWN.toString();
            }
        }
        if (divX < 0) {
            if (ifPresentBarrier(board, board.getHead(), divX, 0)) {
                return Direction.LEFT.toString();
            }
        }
        if (divY > 0) {
            if (ifPresentBarrier(board, board.getHead(), 0, divY)) {
                return Direction.UP.toString();
            }
        }
        //двигаемся в первую попавшейся пустую ячейку
        if (ifPresentBarrier(board, board.getHead(), 1, 0)) {
            return Direction.RIGHT.toString();
        }
        if (ifPresentBarrier(board, board.getHead(), 0, -1)) {
            return Direction.DOWN.toString();
        }
        if (ifPresentBarrier(board, board.getHead(), -1, 0)) {
            return Direction.LEFT.toString();
        }
        if (ifPresentBarrier(board, board.getHead(), 0, 1)) {
            return Direction.UP.toString();
        }

        return Direction.LEFT.toString();
    }
        //проверяем или находиться что-то в ящейке
        public static boolean ifPresentBarrier(Board board, Point point, int divX, int divY) {
            if (ifPresentStone(board, board.getStones(), point, divX, divY)) {
                if (ifPresentStone(board, board.getWalls(), point, divX, divY)) {
                    if (ifPresentStone(board, board.getSnake(), point, divX, divY)) {
                        return true;
                    }
                }
            }
            return false;
        }
        //проверка на камень в клетке, в которую займет при следующем ходе голова
         public static boolean ifPresentStone(Board board, List<Point> stones, Point head, int divX, int divY) {
         for (Point stone : stones) {
            if (head.getX() + divX == stone.getX() && head.getY() + divY == stone.getY()) {
                return false;
            }
         }
        return true;
    }
    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://104.248.24.36/codenjoy-contest/board/player/ol.leichenko_@gmail.com?code=406993911952119867",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
