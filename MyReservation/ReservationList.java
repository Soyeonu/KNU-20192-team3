public class ReservationList {

    private int r_id;
    private int roomnum;
    private String r_time;
    private String r_date;
    private int nop;


    public ReservationList(int id, int room, String time, String date, int n)
    {
        this.r_id = id;
        this.roomnum = room;
        this.r_time = time;
        this.r_date = date;
        this.nop = n;
    }

    public int getR_id()
    {
        return this.r_id;
    }

    public int getRoomnum(){
        return this.roomnum;
    }

    public String getR_date()
    {
        return this.r_date;
    }

    public String getR_time(){
        return this.r_time;
    }

    public int getNop()
    {
        return this.nop;
    }


}
