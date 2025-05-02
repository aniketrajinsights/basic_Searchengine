<%@page import = "java.util.ArrayList"%>
<%@page import = "searchengineapp.SearchResult"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles.css">
<style>
    body {
        font-family: 'Arial', sans-serif;
        background: linear-gradient(135deg, #95a5a6, #34495e);
        margin: 0;
        padding: 40px 20px;
        min-height: 100vh;
        overflow-x: hidden;
        overflow-y: auto;
        position: relative;
        background: linear-gradient(135deg, #31475c, #43b3c5);
        /* background: linear-gradient(135deg, #95a5a6, #34495e); */
            transform: translateY(-2px);
    }

    .background-design {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        z-index: -1;
        overflow: hidden;
    }

    .circle {
        position: absolute;
        border-radius: 50%;
        background: rgba(255, 255, 255, 0.2);
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
        animation: float 4s ease-in-out infinite;
    }

    .circle:nth-child(1) { width: 150px; height: 150px; top: 10%; left: 20%; }
    .circle:nth-child(2) { width: 100px; height: 100px; top: 50%; left: 70%; }
    .circle:nth-child(3) { width: 200px; height: 200px; top: 70%; left: 10%; }
    .circle:nth-child(4) { width: 120px; height: 120px; top: 30%; left: 40%; }
    .circle:nth-child(5) { width: 80px; height: 80px; top: 60%; left: 80%; }
    .circle:nth-child(6) { width: 180px; height: 180px; top: 15%; left: 75%; }
    .circle:nth-child(7) { width: 90px; height: 90px; top: 20%; left: 50%; }
    .circle:nth-child(8) { width: 140px; height: 140px; top: 40%; left: 30%; }
    .circle:nth-child(9) { width: 110px; height: 110px; top: 80%; left: 60%; }
    .circle:nth-child(10) { width: 70px; height: 70px; top: 5%; left: 90%; }

    @keyframes float {
        0%, 100% { transform: translateY(0); }
        50% { transform: translateY(-20px); }
    }

    .container {
        background: rgba(255, 255, 255, 0.1);
        padding: 20px 30px;
        border-radius: 10px;
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.4);
        text-align: center;
        max-width: 800px;
        margin: auto;
    }

    h1 {
        font-size: 24px;
        
        color:   #800000;
        color: #ecf0f1;
        margin-bottom: 20px;
        text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
    }

    form {
        margin-bottom: 20px;
    }

    input[type="text"] {
        width: 70%;
        padding: 10px;
        margin-bottom: 10px;
        border: 1px solid #7f8c8d;
        border-radius: 5px;
        font-size: 14px;
        background: #ecf0f1;
        color: #2c3e50;
    }

    button {
        background: linear-gradient(135deg, #7f8c8d, #2c3e50);
        color: #ecf0f1;
        border: none;
        padding: 10px 15px;
        border-radius: 5px;
        font-size: 14px;
        cursor: pointer;
        transition: background 0.3s ease, transform 0.2s ease;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
    }

    button:hover {
        background: linear-gradient(135deg, #95a5a6, #34495e);
        transform: translateY(-2px);
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        background: rgba(255, 255, 255, 0.2);
        color: #ecf0f1;
    }

    td, th {
        padding: 10px;
        border: 1px solid #7f8c8d;
    }

    a {
        color: #00008B;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }
</style>
</head>
<body>
<div class="background-design">
    <div class="circle"></div>
    <div class="circle"></div>
    <div class="circle"></div>
    <div class="circle"></div>
    <div class="circle"></div>
    <div class="circle"></div>
    <div class="circle"></div>
    <div class="circle"></div>
    <div class="circle"></div>
    <div class="circle"></div>
</div>
<div class="container">
    <h1>Search Results</h1>
    <form action="Search">
        <input type="text" name="keyword" placeholder="Enter keyword" autocomplete="off">
        <button type="submit">Search</button>
    </form>
    <div class="resultTable">
        <table>
            <tr>
                <th>Title</th>
                <th>Link</th>
            </tr>
            <%
                ArrayList<SearchResult> results = (ArrayList<SearchResult>)request.getAttribute("results");
                for(SearchResult result:results){
            %>
			<tr>
                <td><% out.println(result.getPageTitle()); %></td>
                <td><a href="<% out.print(result.getPageLink()); %>"><% out.print(result.getPageLink()); %></a></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</div>
</body>
</html>
