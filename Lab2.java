import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Lab2 extends Course
{
    private int lecture_id = 0;

    public Lab2(String name, int number, int section)
    {
        // some lectures only have 1 section, so we don't need to get the lecture section for labs/tuts
        super(name, number, section);
    }

    public Lab2(String name, int number, int section, int lecture)
    {
        super(name, number, section);
        lecture_id = lecture;
    }

    public int getLectureSection()
    {
        return lecture_id;
    }
}
