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
import org.apache.commons.lang.ObjectUtils;

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

        if (headerY < appleY){
            path = Direction.UP.toString();
            divY = -1;
        }
        else if (headerY > appleY){
            path = Direction.DOWN.toString();
            divY = 1;
        }
        else if ( headerX < appleX){
            path = Direction.RIGHT.toString();
            divX = 1;
        }
        else if ( headerX > appleX){
            path = Direction.LEFT.toString();
            divX = -1;
        }
        nextX = board.getHead().getX() + divX;
        nextY = board.getHead().getY() + divY;

        for (Point i: board.getSnake()) {
            if( i  )
        }

        return path;
    }








//    public static String putDirectionToNextPoint(Point fromPoint, Point toPoint) {
//        if (fromPoint.getY() < toPoint.getY()) {
//            return Direction.UP.toString();
//        }
//        if (fromPoint.getY() > toPoint.getY()) {
//            return Direction.DOWN.toString();
//        }
//        if (fromPoint.getX() < toPoint.getX()) {
//            return Direction.RIGHT.toString();
//        }
//        return Direction.LEFT.toString();
//    }
//    public static Point pointPlusXY(Point point, int divX, int divY) {
//        return new PointImpl(point.getX() + divX, point.getY() + divY);
//    }





    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://104.248.24.36/codenjoy-contest/board/player/ol.leichenko@gmail.com?code=2142052770436339034",
                new YourSolver(new RandomDice()),
                new Board());

    }

}
