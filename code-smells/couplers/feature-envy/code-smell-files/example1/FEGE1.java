import java.time.Year;

enum Genre {
    POP,
    ROCK,
    HIPHOP,
    RNB,
    EDM
}

enum PlaybackState {
    STOPPED,
    PLAYING,
    PAUSED
}

class Record {
    private String name;
    private Genre genre;
    private int duration;
    private Year releaseYear;

    public Record(String name, Genre genre, int duration, Year releaseYear) {
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.releaseYear = releaseYear;
    }

    public String getName() {
        return this.name;
    }

    public boolean isReleased() {
        return this.releaseYear.isBefore(Year.now()) || this.releaseYear.equals(Year.now());
    }

    public int getDuration() {
        return this.duration;
    }

    public Genre getGenre() {
        return this.genre;
    }

    private String getFormattedDuration() {
        int minutes = this.duration / 60;
        int seconds = this.duration % 60;
        return String.format("%d min %02d sec", minutes, seconds);
    }

    public String getRecordDetails() {
        String details = this.name + " - " + this.genre + " - " + getFormattedDuration();
        return details;
    }

}

class RecordPlayer {
    private Record currentRecord;
    private PlaybackState state;

    public RecordPlayer(Record initialRecord) {
        this.currentRecord = initialRecord;
        this.state = PlaybackState.STOPPED;
    }

    public void playRecord() {
        if (this.state == PlaybackState.PLAYING) {
            System.out.println("Already playing");
        } else {
            System.out.println("Playing: " + currentRecord.getName());
            this.state = PlaybackState.PLAYING;
        }
    }

    public void stopRecord() {
        if (this.state == PlaybackState.STOPPED) {
            System.out.println("Already stopped");
        } else {
            System.out.println("Stopped playing: " + currentRecord.getName());
            this.state = PlaybackState.STOPPED;
        }
    }

    public void pauseRecord() {
        if (this.state == PlaybackState.PAUSED) {
            System.out.println("Already paused");
        } else {
            System.out.println("Paused: " + currentRecord.getName());
            this.state = PlaybackState.PAUSED;
        }
    }

    public void changeRecord(Record newRecord) {
        if (this.state != PlaybackState.STOPPED) {
            System.out.println("Please stop playing to change the record");
        } else {
            this.currentRecord = newRecord;
            this.state = PlaybackState.PLAYING;
            System.out.println("Started playing: " + this.currentRecord.getName());
        }
    }
}

public class FEGE1 {
    public static void main(String[] args) {
        Record stairwayToHeaven = new Record("Stairway to Heaven", Genre.POP, 482, Year.of(1971));
        Record saySo = new Record("Say So", Genre.RNB, 238, Year.of(2019));
        RecordPlayer sony = new RecordPlayer(saySo);
        sony.pauseRecord();
        sony.playRecord();
        sony.changeRecord(stairwayToHeaven);
        sony.stopRecord();
        sony.changeRecord(stairwayToHeaven);
        System.out.println(stairwayToHeaven.getRecordDetails());
        sony.playRecord();
    }
}