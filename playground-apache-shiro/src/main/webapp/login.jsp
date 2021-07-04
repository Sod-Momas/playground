<html>
<head>
    <title>login page</title>
</head>
<body>
<form action="/account/login" method="post" enctype="application/x-www-form-urlencoded">
    <label for="username">username</label>
    <input type="text" name="username" id="username" value="sod">
    <br>
    <label for="password">password</label>
    <input type="password" name="password" id="password" value="123456">
    <br>
    <input type="submit" value="login">
    <input type="reset" value="reset">
</form>

<script>

    if(location.search.startsWith('?msg='))
    {
    var msg = location.search.substr(5) || '';
    alert(msg);
    }

</script>

</body>
</html>