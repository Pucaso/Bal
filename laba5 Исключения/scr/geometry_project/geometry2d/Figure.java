package geometry2d;

public interface Figure {
    double Area() throws Exceptions.InvalidFigureException;
    void Show() throws Exceptions.InvalidFigureException;
}