function getCookie(name){
    let strCookie = document.cookie;//获取cookie字符串
    let arrCookie = strCookie.split(";");//分割
    //遍历匹配
    for ( let i = 0; i < arrCookie.length; i++) {
        let s = arrCookie[i].trim();
        let arr = s.split("=");
        if (arr[0] == name){
            return arr[1];
        }
    }
    return "";
}

function setCookie(k,v){
    document.cookie = k + "=" + v;
}