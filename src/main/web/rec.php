
<?php
require 'connect.php'; // Подключает файл с логином/паролем и именем БД
mysql_set_charset('utf8'); // Устанавливает кодировку клиента
$author = trim($_REQUEST['author']); // Получает содержимое поля "Автор" и убирает возможные пробелы в начале строки
$title = trim($_REQUEST['title']); // То же самое для поля "Название"
$text = mysql_real_escape_string(trim($_REQUEST['text'])); // То же самое для поля "Текст" + (см.ниже)
$insert_sql = "INSERT INTO polka1 (author, title, text)" . // Указывает в какую таблицу и в какие поля ...
                   "VALUES('{$author}', '{$title}', '{$text}');"; // ...записывать данные
mysql_query($insert_sql); // отправляем данные в базу
?>
<html>
<head>
<META HTTP-EQUIV='Refresh' CONTENT='1,URL=index.html'> <!-- Возврат обратно -->
</head>
<body>
</body>
</html>