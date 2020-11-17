/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    let content = $("#comment_content").val();
    Comment2target(questionId, 1, content);
    console.log(questionId);
    console.log(content);
}

function Comment2target(targetId, type, content) {
    if (content == null||content=="") {
        alert("内容不能为空");
        return;
    }
    $.ajax({
        type: "post",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parentId": targetId,
            "content": content,
            "type": type,
        }),
        success: function (response) {
            if (response.code == 200) {
                //刷新页面
                window.location.reload();
                $("#comment_section").hide();
            } else {
                if (response.code == 2003) {
                    let isAccepted = confirm(response.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.75c1e6b5781e0b66&state=STATE&redirect_uri=http://localhost:8012/callback&state=1")
                        window.localStorage.setItem("closeable", "true");
                    }
                } else {
                    alert(response.message);
                }
            }
            console.log(response)
        },
        dataType: "json"
    });
}

function comment(e) {
    let commentId = e.getAttribute("data-id");
    let content = $("#input-" + commentId).val();
    Comment2target(commentId, 2, content);
}

/**
 * 展开二级回复
 */

function collapseComments(e) {
    let id = e.getAttribute("data-id");
    console.log(id);
    let comments = $("#comment-" + id);
    //获取评论展开状态
    let collapse = e.getAttribute("data-collapse");
    if (collapse) {
        //隐藏
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        //显示
        comments.addClass("in");
        //标记评论状态
        e.setAttribute("data-collapse", "in");
        e.classList.add("active");
    }
}

function showSelectTAag() {
    $("#select-tag").show();
}


function selectTag(e) {
    let value = e.getAttribute("data-tag");
    let previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + ',' + value);
        } else {
            $("#tag").val(value);
        }
    }
}
