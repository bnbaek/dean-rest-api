<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <title>welcome</title>

  <script>
    function popupKakaoLogin() {
      <#noautoesc>
      window.open('${loginUrl}', 'popupKakaoLogin', 'width=700,height=500,scrollbars=0,toolbar=0,menubar=no')
      </#noautoesc>
      <#--window.open('${loginUrl}', 'popupKakaoLogin', 'width=700,height=500,scrollbars=0,toolbar=0,menubar=no')-->
    }
  </script>
</head>
<body>
<button onclick="popupKakaoLogin()">KakaoLogin</button>


</body>
</html>