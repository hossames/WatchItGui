package src.ContentControl;

public interface Rateable {
    public void AddRate(int UserId,float Rate);
    public void EditRate(float Rate);
    public void InitRate();
    public long TotalRate();
}
