/**
 * This class represents a Coordinate point.
 *
 * @author Marcos Traverso
 */
public class Coordinate
{
    private final int x;
    private final int y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public Coordinate getForward()
    {
        if (x == 8) {
            return new Coordinate(0, y + 1);
        }
        return new Coordinate(x + 1, y);
    }

    public Coordinate getBackward()
    {
        if (x == 0) {
            return new Coordinate(8, y - 1);
        }
        return new Coordinate(x - 1, y);
    }

    @Override
    public String toString()
    {
        return String.format("(%s, %s)", x, y);
    }
}
