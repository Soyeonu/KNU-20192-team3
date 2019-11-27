package lectureRoom;

public class Reservation {

    private String time;
    int numofperson;
    int roomnum;


    public Reservation(int r, String t, int n)
    {
        this.numofperson = n;
        this.time = t;
        this.roomnum = r;
    }

    public void print()
    {
        System.out.println("room:"+roomnum+", time:"+time+", nop:"+numofperson);
    }

    public int getNumofperson()
    {
        return this.numofperson;
    }

}