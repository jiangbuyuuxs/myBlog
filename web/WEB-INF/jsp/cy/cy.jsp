<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成语</title>
    <%@include file="../comm/head.jsp" %>
    <script>
        $(function () {
            $(".cy").on("keydown", function (e) {
                if (e.keyCode == 13) {
                    var cy = $(this).val();
                    getNextCy(cy);
                }
            });

            $(document).on("click", ".next-cy .list-group-item", function () {
                var cy = $(this).text();
                $(".cy").val(cy);
                getNextCy(cy);
            });
            $(".next-cy-btn").on("click", function () {
                var cy = $(".cy").val();
                getNextCy(cy);
            });

            function getNextCy(cy) {
                if (cy && cy.length > 2) {
                    $.ajax("/cy/cy/cy", {
                        type: "POST",
                        dataType: "json",
                        data: {cy: cy},
                        success: function (data) {
                            $(".next-cy").empty();
                            if (data.success) {
                                if (data.num == 0) {
                                    alert("是在下输了~~");
                                } else {
                                    var cyarr = data.cy;
                                    if (cyarr) {
                                        var i = 0, j = cyarr.length;
                                        for (; i < j; i++) {
                                            $("<li class='list-group-item' pyend='" + cyarr[i].pyend + "'>" + cyarr[i].cy + "</li>").appendTo($(".next-cy"));
                                        }
                                    }
                                }
                            }
                        }
                    });
                }else{
                    $(".cy").val("");
                    $(".next-cy").empty();
                }
            }

        });
    </script>
</head>
<body class="container">
<div class="row">
    <div class="col-offset-4 col-md-4">
        <div class="input-group">
            <input name="cy" class="cy form-control" type="text" placeholder="成语">
            <span class="input-group-btn">
            <a class="btn btn-default next-cy-btn" type="button">来一下</a>
          </span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-offset-4 col-md-4">
        <ul class="list-group next-cy"></ul>
    </div>
</div>
</body>
</html>
