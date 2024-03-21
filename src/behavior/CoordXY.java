package behavior;

import person.PersonBase;

public class CoordXY {
    private int curX;
    private int curY;

    private static int width;
    private static int height;

    static {
        width = 10;
        height = 10;
    }

    public CoordXY(int x, int y)
    {
        curX = x;
        curY = y;
    }

    public void setXY(int x, int y)
    {
        curX = x;
        curY = y;
    }

    public int getX() {
        return curX;
    }

    public int getY() {
        return curY;
    }

    public static void setWidth(int width) {
        CoordXY.width = width;
    }

    public static void setHeight(int height) {
        CoordXY.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


    public void increment(int dx, int dy)
    {
        curX += dx;
        curY += dy;
    }
    public boolean isMove(int x, int y)
    {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
    public float distanceTo(CoordXY target)
    {
        float x = curX - target.getX();
        float y = curY - target.getY();
        return (float) Math.sqrt(x*x + y*y);
    }

    public float fastDistance(CoordXY target, int dx, int dy)
    {
        float tx = curX+dx - target.getX();
        float ty = curY+dy - target.getY();
        return (tx*tx + ty*ty);
    }
    public CoordXY getDelta(CoordXY to)
    {
        return new CoordXY(to.curX-curX, to.curY-curY);
    }
    public boolean equal(CoordXY to)
    {
        return curX == to.curX && curY == to.curY;
    }

    public String toString()
    {
        return curX + ":" + curY;
    }
}