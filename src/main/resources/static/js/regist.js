    var count=60;
    function counts(){
        if(count==0){
            $("#get_code").val("获取验证码");
            count=60;
            $("#get_code").removeAttr("disabled");

            return false;
        }else{
            $("#get_code").attr("disabled","disabled");
            $("#get_code").val(count+"s后重新获取");
            count--;
        }
        setTimeout(function () {
            counts();
        },1000);
    }
    $(document).ready(function () {
        $("#alerts").empty();
        $("#get_code").click(function () {
            var get_code_url="/regist/code";
            var mail=$("#email").val();
            var reg = /^[a-zA-Z0-9]+@[a-z0-9]{2,5}\.[a-z]{2,3}(\.[a-z]{2,3})?$/;
            var result = reg.test(mail);
            if(!result){
                alert("请输入正确的邮箱！")
                return;
            }
             counts();
             $.ajax({
                 type: "GET",
                 url:get_code_url,
                 dataType:'json',
                 contentType: "application/json",
                 data:{
                     email:mail
                 },
                 success:function(resData) {
                      ;
                     if(resData.code==="200验证码已发送"){
                         counts(this);
                         $("#alerts").empty();
                     }
                     else{
                         $("#alerts").empty();
                         $("#alerts").append("<div id='myAlert' class='alert alert-success'>\n" +
                             "                        <a href='#' class='close' data-dismiss='alert'>&times;</a>\n" +
                             "                    <strong>error!</strong>"+resData.code+"\n" +
                             "                    </div>")
                         return false;
                     }
                 },
                 error:function(resData){
                     $("#alerts").empty();
                     $("#alerts").append("<div id='myAlert' class='alert alert-success'>\n" +
                         "                        <a href='#' class='close' data-dismiss='alert'>&times;</a>\n" +
                         "                    <strong>error!</strong>Internet error!\n" +
                         "                    </div>")
                     return false;
                 }
             })

        })
        $("#check").click(function () {
            $("#alerts").empty();
            var mail=$("#email").val();
            var acode=$("#email_code").val();
            $.ajax({
                type: "GET",
                url:"/regist/check",
                dataType:'json',
                contentType: "application/json",
                data:{
                    email:mail,
                    code:acode
                },
                success:function(resData) {
                    if(resData.mes !=null){
                        if("验证成功"==resData.mes){

                            $("#check").text("下一步");
                            $("#alerts").empty();
                            $("#alerts").append("<div id='myAlert' class='alert alert-success'>\n" +
                                "                        <a href='#' class='close' data-dismiss='alert'>&times;</a>\n" +
                                "                    <strong>success!</strong>"+resData.mes+"\n" +
                                "                    </div>");
                            $("#check").attr({
                                "href":"#check-code",
                                "id":"false"
                            });

                        }
                        else
                        {
                            $("#alerts").empty();
                            $("#alerts").append("<div id='myAlert' class='alert alert-success'>\n" +
                                "                        <a href='#' class='close' data-dismiss='alert'>&times;</a>\n" +
                                "                    <strong>error!</strong>"+resData.mes+"\n" +
                                "                    </div>");
                            return false;
                        }
                    }
                },
                error:function () {
                    return false;
                }
            });
        })

        $("#finish").click(function () {
            $("#alerts").empty();
            var mail=$("#email").val();
            var password=$("#password").val();
            var repassword=$("#repassword").val();
            if(password!==repassword){
                $("#alerts").empty();
                $("#alerts").append("<div id='myAlert' class='alert alert-success'>\n" +
                    "                        <a href='#' class='close' data-dismiss='alert'>&times;</a>\n" +
                    "                    <strong>error!</strong>两次密码不一样！\n" +
                    "                    </div>");
                return false;
            }
            $.ajax({
                type: "POST",
                url:" /regist/finish",
                contentType:"application/json",
                data:JSON.stringify({
                    username:mail,
                    password:password
                }),
                success:function(resData) {
                    var res=resData;
                    if("注册成功"==res.mes){
                        $("#alerts").empty();
                        $("#alerts").append("<div id='myAlert' class='alert alert-success'>\n" +
                            "                        <a href='#' class='close' data-dismiss='alert'>&times;</a>\n" +
                            "                    <strong>成功!</strong>"+res.mes+"\n" +
                            "                    </div>");
                    }
                    else {
                        $("#alerts").empty();
                        $("#alerts").append("<div id='myAlert' class='alert alert-success'>\n" +
                            "                        <a href='#' class='close' data-dismiss='alert'>&times;</a>\n" +
                            "                    <strong>error!</strong>"+res.mes+"\n" +
                            "                    </div>");
                        return false;
                    }
                },
                error:function(res){
                    $("#alerts").empty();
                    $("#alerts").append("<div id='myAlert' class='alert alert-success'>\n" +
                        "                        <a href='#' class='close' data-dismiss='alert'>&times;</a>\n" +
                        "                    <strong>error!</strong>Internet error!\n" +
                        "                    </div>");
                    return false;
                }
            });
        })
    })






