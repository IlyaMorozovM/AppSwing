# AppSwing
laba 1
Построить иерархию классов для вывода графических фигур: отрезок, прямоугольник, эллипс и т.д. Распределить классы по модулям. Создать список фигур в виде отдельного класса. В главном модуле программно добавить в список различные фигуры. Запустить рисование списка. Для рисования использовать любую подходящую графическую библиотеку. Допускается вывод фигур в файл или на экран в виде текстовых строк вида "Rectangle(10, 20, 30, 40)".

laba 2
Расширить пример с графическими фигурами так, чтобы фигуры можно было создавать на уровне пользовательского интерфейса. Можно выбрать любой способ: ввод координат с помощью мыши, диалоговый ввод значений, ввод на скриптовом языке. Создание объекта должно выполняться так, чтобы добавление нового класса в систему не требовало изменения существующего кода (выбор с помощью оператора switch-case делать нельзя). Получившаяся программа должна представлять собой примитивный графический редактор.

Выбрать фигуру - права кнопка мыши, рисовать - левой кнопкой устанавливать точки на экране, очистить экран - нажать на колесико, поменять цвет при рисовании на случайный - прокрутить колесико.

laba 3
Добавить в графический редактор возможность сохранять список графических объектов в файл и восстанавливать его из файла. Пользователь должен получить возможность расширять созданные ранее графические файлы новыми фигурами. При сериализации учесть, что добавление нового класса в систему не должно потребовать изменения существующего кода  (выбор с помощью оператора switch-case делать нельзя).

Добавлена фигура полигон.

laba 4
Плагины
Доработать графический редактор таким образом, чтобы в него можно было добавить новый модуль с классом Trapezium (доработан). Добавление нового модуля должно произойти таким образом, чтобы в графическом редакторе не изменилась ни одна строчка кода. Допускается лишь запись в главном модуле одной строки с именем нового модуля и перекомпиляция. В идеале добавление нового модуля должно выполняться его динамической загрузкой, т.е. вообще не должно требовать изменения кода программы (функционал реализован). 

Также реализовано корректное отображение ошибки отсутствия нужного плагина при загрузке изображения.
