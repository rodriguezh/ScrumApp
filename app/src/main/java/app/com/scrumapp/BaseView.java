package app.com.scrumapp;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showInfoMessage(String respuesta);
}
