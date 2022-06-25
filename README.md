# Steganography
Steganography (Educational project)

Стеганография
1. Реализовать приложение, которое извлекает данные из входного файла, учитывая следующий алгоритм шифрования. Для скрытной передачи данных используется такое свойство символа, как регистр. Если буква в нижнем регистре – это соответствует «0», если буква в заглавном регистре – это соответствует «1». Очередной символ секретного сообщения составляется из 8-ми битов, которые формируют код этого символа. В шифровании используются только буквы русского или английского алфавита. Знаки препинания и цифры, а так же буквы ё, Ё, ч, Ч не учитываются.
Приложение на входе получает имя текстового файла и кодировку текста, анализирует текст по регистрам букв и извлекает из него секретное сообщение, после чего выводит сообщение на экран. Необходимо произвести обработку всех исключительных ситуаций в главной части программы.
Указание: 
Для проверки символа, является ли он буквой, используйте функцию 	Character.isLetter
Для перевода символов в верхний и нижний регистр используйте функции 
Character.toUpper/Character.toLower
Для корректной работы строки, содержащие русские буквы, должны быть объявлены как char. Учесть, что в Java символы кодируются 2 байтами в типе данных char, а в задаче один символ кодируется только 1 байтом.

Тестовые данные:
Файлы с сообщениями располагаются в папке MESSAGES. 

	2. Реализовать программу, которая принимает на вход имя файла, где располагается сообщение для шифрования, имя файла, где располагается текст, в котором будет происходить шифрование, имя файла, куда будет записан зашифрованное сообщение, а также кодировку, в которой производится шифрование. Необходимо произвести шифрование сообщения по правилам, описанным в 1 пункте. При невозможности произвести шифрование в переданном тексте сгенерировать исключение InsufficientTextSizeException.

При реализации 1 и 2 пунктов проекта задачу необходимо разбить на статические методы, открытыми сделать лишь те методы, которые должны будут вызываться пользователем. Все остальные служебные вспомогательные методы необходимо закрыть. ПРЕДУСМОТРЕТЬ ВОЗМОЖНОСТЬ ВВОДА НЕ ТОЛЬКО ИМЕН ФАЙЛОВ ДЛЯ ВВОДА ИНФОРМАЦИИ, А ТАК ЖЕ ВВОДА И СТРОК В МЕТОДЫ. ЭТОГО МОЖНО ДОСТИЧЬ ЛИШЬ В ТОМ СЛУЧАЕ, КОГДА МЕТОД СЧИТЫВАНИЯ С ФАЛА БУДЕТ ОТДЕЛЕН ОТ МЕТОДА ШИФРОВАНИЯ.

	3. Реализовать простой и понятный интерфейс, позволяющий продемонстрировать правильность работы приложения. Использовать методы как для ввода имен файлов, так и данных с клавиатуры.