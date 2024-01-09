<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>

    <header>
        <h1>Welcome to Joke API</h1>
    </header>

    <main>
        <form action="addJoke" method="get">
            <textarea name="Joke" id="single_joke" cols="50" rows="10" placeholder="Enter your joke here"></textarea>
            <button type="submit"></button>
        </form>
    </main>

    <footer></footer>

</body>
</html>
