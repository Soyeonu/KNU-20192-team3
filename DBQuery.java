import java.sql.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class DBQuery {
    ClassRoom[] rooms; //현 층수에 대한 강의실 정보
    Reservation[] res;//예약 등록
    ReservationList[] rselist; //예약 조회
    Member myinfo; //로그인 정보 다른 곳에서 사용하고 싶으면 DBQuery.myinfo로 할 것!
    Search search;


    int roomnumber; //강의실 호수
    int rsenumber;
    int nop; //예약 인원 수
    int cnt = 0;
    int schedulecnt=0;
    String d = null; //date
    String t = null; //time or temp


    ConnectDB db = new ConnectDB();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;
    PreparedStatement pstmt = null;


    String query = null;

    public void setRegister(String name, int id) //회원가입 -> 최지훈
    {
        try {
            conn = db.getMySQLConnection();
            query = "INSERT INTO mydb.member (S_ID,S_NAME) VALUES (? , ?);";
            pstmt = conn.prepareStatement(query);


            pstmt.setInt(1,id);
            pstmt.setString(2,name);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(stmt);
            db.close(rset);
            db.close(conn);
        }
    }

    public int setMemInfo(int id) //로그인 -> 최지훈
    {
        int myid;
        String myname;
        int result = 0;


        try {
            conn = db.getMySQLConnection();
            stmt = conn.createStatement();
            query = "select * from mydb.member where S_ID = "+id+";";
            rset = stmt.executeQuery(query);

            if (rset != null) {
                while (rset.next()) { //현재 시간에 대한 강의실 호수 -> 호수 정보를 알고 싶으면 DBQuery.rooms[i]를 통해서 가져오기
                    myid = rset.getInt(1);
                    myname = rset.getString(2);
                    myinfo = new Member(myid,myname);
                    result=1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(stmt);
            db.close(rset);
            db.close(conn);
        }

        return result;
    }

    public void setSearch(int day, Time time, int floor) { //검색 강의실 정보 -> 박재용
        int i = 0;
        try {

            rooms = new ClassRoom[10];
            schedulecnt=0;

            System.out.println("day :: "+ day + "time ::: "+ time + "floor :: "+ floor);
            conn = db.getMySQLConnection();
            stmt = conn.createStatement();

            query = "select * from mydb.schedule where L_DAY = "+day+" and "+"L_S_TIME = \'"+time + "\' and " + "L_FLOOR = " + floor +";";

            rset = stmt.executeQuery(query);

            if (rset != null) {
                while (rset.next()) { //현재 시간에 대한 강의실 호수 -> 호수 정보를 알고 싶으면 DBQuery.rooms[i]를 통해서 가져오기
                    roomnumber = rset.getInt(2);
                    rooms[i] = new ClassRoom(roomnumber);
                    schedulecnt++;
                    i++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(stmt);
            db.close(rset);
            db.close(conn);
        }

        i=0;
    }



    public void setReservation(int room) //현 강의실 예약 현황 -> 박소연
    {
        int i = 0;
        try {
            res = new Reservation[10];
            conn = db.getMySQLConnection();
            stmt = conn.createStatement();

            query = "select * from mydb.reservation where  ROOMNUM = "+ room;
            rset = stmt.executeQuery(query);

            if (rset != null) {
                while (rset.next()) {
                    roomnumber = rset.getInt(2); //강의실 호수
                    t = rset.getString(3); //시간
                    nop = rset.getInt(5); //예약 인원
                    res[i] = new Reservation(roomnumber, t, nop);
                    i++;
                    cnt++;
                }
            }
            i = 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(stmt);
            db.close(rset);
            db.close(conn);
        }
    }

    public void insertReservation(int n, int r, String t, String d) //예약 확정 -> 박소연
    {
        try {
            conn = db.getMySQLConnection();
            query = "INSERT INTO mydb.reservation (R_ID, ROOMNUM, R_TIME, R_DATE, NOP, MEMBER_S_ID) VALUES (?,?,?,?,?,?);";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1,1111);
            pstmt.setInt(2,r);
            pstmt.setInt(5,n);
            pstmt.setTime(3,java.sql.Time.valueOf(t));
            pstmt.setDate(4,java.sql.Date.valueOf(d));
            pstmt.setInt(6,myinfo.getId());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(stmt);
            db.close(rset);
            db.close(conn);
        }
    }

    public void setRselist(int id) //나의 예약 조회 -> 오현경
    {
        int i = 0;
        try {
            rselist = new ReservationList[10];
            conn = db.getMySQLConnection();
            stmt = conn.createStatement();

            query = "select * from mydb.reservation where MEMBER_S_ID = " + id +";";
            rset = stmt.executeQuery(query);

            if (rset != null) {
                while (rset.next()) {
                    rsenumber = rset.getInt(1);
                    roomnumber = rset.getInt(2);
                    t = rset.getString(3);
                    if(t.charAt(3) != '0' && t.charAt(4)!='0')
                        t = t.substring(0,2) + "시 "+t.substring(3,5)+"분";
                    else
                        t = t.substring(0,2) + "시";
                    d = rset.getString(4);
                    d=d.substring(5,7)+"월 "+d.substring(8,10) +"일";
                    nop = rset.getInt(5);
                    rselist[i] = new ReservationList(rsenumber,roomnumber,t,d,nop);
                    cnt++;
                    i++;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(stmt);
            db.close(rset);
            db.close(conn);
        }
    }

    public void Update( int del) //예약 취소 -> 오현경
    {
        try {
            conn = db.getMySQLConnection();
            stmt = conn.createStatement();

            query = "delete from mydb.reservation where R_ID ="+del+";";

            stmt.executeUpdate(query);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close(stmt);
            db.close(rset);
            db.close(conn);
        }
    }
}
