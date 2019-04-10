package client;

import client.controller.MVController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//проблемы с ChessSet , как без дублирования использовать разные классы
//проблем не обнаружено
//проверка на мат не тестировалась(1 тест)
//добавить список срубленных фигур(сейчас происходит потеря ссылки)
//составить список имен для свойств модели
//осчет поля происходит с верхнего левого угла


//BoardSquare Connection: SQUAREij
//
// Properties:
// PIECE
// PIECE_COLOR4

//Неправильная рокировка
//Проверка мата , исключения...//не факт(пофикшено)
//проверка при мате неправильна
//ужасно реализовано добавление новой фигуры , внедрить на уровне модели
public class ClientApp {

    static Object o;

    public static void main(String[] args) {
        MVController m = new MVController();
        m.run();
    }
}
