public class Cell
{
    public final int value;
    final boolean modifiable;

    public Cell(int value, boolean modifiable)
    {
        this.value = value;
        this.modifiable = modifiable;
    }
    public Cell(){
        this.value = 0; //0 as a base value, will be replaced anyways in solving since its "modifiable"
        this.modifiable = true;
    }

    public boolean isModifiable()
    {
        return this.modifiable;
    }

    public int getValue()
    {
        return this.value;
    }

    public Cell getNextValue(){
        return new Cell(value + 1,true);
    }
}
