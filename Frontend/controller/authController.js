window.authController = function ($scope, $http, $location, $routeParams) {
    $scope.signIn = function (username, password) {
        var user = {
            username: username,
            password: password
        }
        $http.post("http://localhost:8080/auth/signin", user).then(function (response) {
            if (response.status == 200) {
                if (response.data.statusCode == 200) {
                    const token = response.data.token;
                    console.log(parseJwt(token));
                    
                    // $window.localStorage.setItem('jwtToken', token); // Lưu token vào localStorage

                    // const decodedToken = parseJwt(token); // Giải mã token
                    // if (decodedToken && decodedToken.role) {
                    //     $window.localStorage.setItem('userRole', decodedToken.role); // Lưu role vào localStorage
                    //     alert("Login successful as " + decodedToken.role);
                    // } else {
                    //     alert("Invalid token structure!");
                    // }

                } else {
                    alert("Somethings wrong !")
                }
            } else {
                console("Ket noi that bai")
            }
        })

    }
    function parseJwt(token) {
        try {
            const base64Url = token.split('.')[1]; // Phần payload nằm giữa
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = atob(base64); // Giải mã Base64
            return JSON.parse(jsonPayload);  // Parse sang object JSON
        } catch (error) {
            console.error("Invalid token:", error);
            return null; // Trả về null nếu token không hợp lệ
        }
    }
}