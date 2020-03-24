public class Cell
{
    public final int value;
    boolean modifiable = true;

    public Cell(int value, boolean modifiable)
    {
        this.value = value;
        this.modifiable = modifiable;
    }
    public Cell(int value){
        this.value = value;
    }

    public boolean isModifiable()
    {
        return this.modifiable;
    }

    public int getValue()
    {
        return this.value;
    }
}
