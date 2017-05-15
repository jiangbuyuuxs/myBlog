<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成语</title>
    <%@include file="../comm/jscss.jsp" %>
    <style>
        .next-cy-item{
            cursor: hand;
            hover:#cecece;
        }
        .nexted-cy li{
            float:left;
            margin:2px 2px;
            border-radius: 4px;
        }
    </style>
    <script>
        $(function () {
            var nexted = [];
            $(".cy").on("keydown", function (e) {
                if (e.keyCode == 13) {
                    initNextCy();
                    var cy = $(this).val();
                    getNextCy(cy);
                }
            });
            $(".next-cy-btn").on("click", function () {
                initNextCy();
                var cy = $(".cy").val();
                getNextCy(cy);
            });

            $(document).on("click", ".next-cy-item", function () {
                var cy = $(this).text();
                $(".cy").val(cy);
                getNextCy(cy);
            });

            $(".export-cy").on("click", exportCy);

            /**
            *开始新一轮的游戏
             */
            function initNextCy(){
                nexted = [];
                $(".next-cy").empty();
                exportCy();
                $(".nexted-cy").empty();
            }

            function getNextCy(cy) {
                var data = BlogTool.addCSRFToken({cy: cy});
                if (cy && cy.length > 2) {
                    $.ajax("/cy/cy/cy", {
                        type: "POST",
                        dataType: "json",
                        data: data,
                        success: function (data) {
                            $(".next-cy").empty();
                            if (data.success) {
                                nexted.push(data.cy.id);
                                if (data.num == 0) {
                                    fail();
                                } else {
                                    var nextcy = data.nextcy;
                                    if (nextcy) {
                                        var i = 0, j = nextcy.length;
                                        for (; i < j; i++) {
                                            $("<li class='list-group-item'><span class='next-cy-item' cyid='"+nextcy[i].id+"' pyend='" + nextcy[i].pyend + "'>" + nextcy[i].cy+"</span>("+nextcy[i].pyend + ")</li>").appendTo($(".next-cy"));
                                        }
                                    }
                                }
                                $(".nexted-cy").append($("<li class='list-group-item text-success'>"+data.cy.cy+"("+data.cy.pyend+")</li>"));
                                dangerUsedCy();
                            }else{
                                fail("这个程序未收录~~");
                            }
                        }
                    });
                }else{
                    fail("是在下输了,您得输入完整的成语");
                }
            }

            function fail(msg){
                msg = msg||"是在下输了~~, "+$(".cy").val()+" 下面没有了.";
                alert(msg);
                initNextCy();
            }

            function dangerUsedCy(){
                var i= 0,j=nexted.length;
                for(;i<j;i++){
                    var next_item = $("[cyid="+nexted[i]+"]");
                    next_item.parent().addClass("hidden");
                }
            }

            function exportCy(){
                var cy_linked = "";
                $(".nexted-cy li").each(function () {
                    cy_linked += $(this).text()+"->";
                });
                console.log(cy_linked+"囧");
            }
        });
    </script>
</head>
<body class="container">
<div class="row">
    <div class="col-md-4">
        <div class="input-group">
            <input name="cy" class="cy form-control" type="text" placeholder="成语" value="自力更生">
            <span class="input-group-btn">
            <a class="btn btn-default next-cy-btn" type="button">来一下</a>
          </span>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-4">
        <ul class="list-group">
            <li class="list-group-item">可选的后续成语</li>
        </ul>
        <ul class="list-group next-cy"></ul>
    </div>
    <div class=" col-md-8">
        <ul class="list-group">
            <li class="list-group-item">已使用过的成语<a tiele="导出结果在console里面." class="btn btn-success btn-xs export-cy pull-right">导出</a> </li>
        </ul>
        <ul class="nexted-cy"></ul>
    </div>
</div>
<%@include file="../comm/footer.jsp"%>
</body>
</html>
