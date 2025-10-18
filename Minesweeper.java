public class Minesweeper implements Comparable<Minesweeper> {
    private enum Reason {UNSET, WON, LOGIC, MISCLICK, MISCOUNT, CHANCE};
    private int month, year, bombs, time;
    private Reason myReason;

    public Minesweeper() {
      month = year = bombs = time = 0;
      myReason = Reason.UNSET;  
    }

    public Minesweeper(int m, int y, int b, int t, int r) {
        month = m;
        year = y;
        bombs = b;
        time = t;
        setReason(r);
    }

    public void setReason(int r) {
        switch(r) {
            case 0: myReason = Reason.UNSET; break;
            case 1: myReason = Reason.WON; break;
            case 2: myReason = Reason.LOGIC; break;
            case 3: myReason = Reason.MISCLICK; break;
            case 4: myReason = Reason.MISCOUNT; break;
            case 5: myReason = Reason.CHANCE; break;
            default: break;
        }
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getBombs() {
        return bombs;
    }

    public int getTime() {
        return time;
    }

    public int getReason() {
        switch(myReason) {
            case UNSET: return 0;
            case WON: return 1;
            case LOGIC: return 2;
            case MISCLICK: return 3;
            case MISCOUNT: return 4;
            case CHANCE: return 5;
            default: return 6;
        }
    }

    @Override
    public String toString() {
        return String.format("%d,%d,%d,%d,%d", month, year, bombs, time, getReason());
    }

    @Override
    public boolean equals(Object obj2) {
        Minesweeper mObj2 = (Minesweeper)obj2;
        return (bombs == mObj2.getBombs() && time == mObj2.getTime());
    }

    @Override
    public int compareTo(Minesweeper m) {
        // < returns -1
        if (time > m.getTime() && bombs <= m.getBombs()) {
            return -1;
        }
        if (bombs < m.getBombs() && time >= m.getTime()) {
            return -1;
        }
        // > returns 1
        if (time < m.getTime() && bombs >= m.getBombs()) {
            return 1;
        }
        if (bombs > m.getBombs() && time <= m.getTime()) {
            return 1;
        }
        else return 0;
    }
}
