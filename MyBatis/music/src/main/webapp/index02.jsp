<%--
  Created by IntelliJ IDEA.
  User: 29298
  Date: 2025/5/20
  Time: 下午2:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: "Microsoft YaHei", sans-serif;
        }

        body {
            display: flex;
            flex-direction: column;
            height: 100vh;
            overflow: hidden;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #2196F3;
            color: white;
            height: 50px;
            padding: 0 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .logo {
            display: flex;
            align-items: center;
        }

        .logo-icon {
            margin-right: 10px;
            font-size: 22px;
        }

        .admin-info {
            display: flex;
            align-items: center;
        }

        .admin-avatar {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            background-color: #ccc;
            margin-right: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
        }

        .admin-avatar img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .main-container {
            display: flex;
            flex: 1;
            overflow: hidden;
        }

        .sidebar {
            width: 150px;
            background-color: #f5f5f5;
            overflow-y: auto;
        }

        .sidebar-menu {
            list-style-type: none;
        }

        .sidebar-menu-item {
            padding: 15px;
            display: flex;
            align-items: center;
            cursor: pointer;
            transition: background-color 0.3s;
            border-left: 3px solid transparent;
        }

        .sidebar-menu-item:hover,
        .sidebar-menu-item.active {
            background-color: #e0e0e0;
            border-left: 3px solid #2196F3;
        }

        .sidebar-menu-item.active {
            font-weight: bold;
        }

        .sidebar-icon {
            margin-right: 10px;
            font-size: 18px;
            width: 20px;
            text-align: center;
        }

        .content {
            flex: 1;
            overflow-y: auto;
            padding: 20px;
            background-color: #f9f9f9;
        }

        .page {
            display: none;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
        }

        .page.active {
            display: block;
        }

        .page-title {
            font-size: 20px;
            margin-bottom: 20px;
            color: #333;
        }

        .chart {
            width: 100%;
            height: 300px;
            background-color: #f9f9f9;
            border-radius: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: #999;
            margin-bottom: 20px;
            overflow: hidden;
            position: relative;
        }

        /* 轮播图样式 */
        .carousel {
            width: 100%;
            height: 100%;
            position: relative;
            aspect-ratio: 16/9;
        }

        .carousel img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            position: absolute;
            top: 0;
            left: 0;
            opacity: 0;
            transition: opacity 0.5s ease-in-out;
            max-width: 100%;
            max-height: 100%;
            object-position: center top;
            transform: scale(0.95);
            transition: transform 0.3s ease;
        }

        .carousel img.active {
            opacity: 1;
            transform: scale(1);
        }

        .carousel-control {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            background-color: rgba(0, 0, 0, 0.5);
            color: white;
            border: none;
            padding: 10px;
            cursor: pointer;
            z-index: 10;
        }

        .carousel-prev {
            left: 10px;
        }

        .carousel-next {
            right: 10px;
        }

        .carousel-indicators {
            position: absolute;
            bottom: 10px;
            left: 50%;
            transform: translateX(-50%);
            display: flex;
            gap: 10px;
        }

        .indicator {
            width: 10px;
            height: 10px;
            border-radius: 50%;
            background-color: rgba(255, 255, 255, 0.5);
            cursor: pointer;
        }

        .indicator.active {
            background-color: white;
        }

        .data-cards {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .data-card {
            flex: 1;
            background-color: white;
            margin: 0 10px;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .data-number {
            font-size: 36px;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .data-label {
            font-size: 16px;
        }

        /* 用户管理样式 */
        .user-management {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.05);
            margin-bottom: 20px;
        }

        .search-bar {
            margin-bottom: 15px;
        }

        .search-input {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        .button-group {
            display: flex;
            margin-bottom: 15px;
        }

        .batch-delete-form,
        .add-user-form {
            margin-right: 10px;
        }

        .batch-delete-button,
        .add-user-button {
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
            background: none;
        }

        .batch-delete-form {
            display: inline-block;
        }

        .add-user-form {
            display: inline-block;
        }

        .batch-delete-button {
            background-color: #ff5252;
            color: white;
        }

        .add-user-button {
            background-color: #4caf50;
            color: white;
        }

        .user-table {
            width: 100%;
            border-collapse: collapse;
            text-align: center;
        }

        .user-table th {
            padding: 12px 15px;
            text-align: center;
            background-color: #f5f5f5;
            border-bottom: 1px solid #ddd;
            font-weight: bold;
            font-size: 14px;
            color: #333;
        }

        .user-table td {
            padding: 12px 15px;
            border-bottom: 1px solid #eee;
            font-size: 14px;
            color: #333;
        }

        .user-avatar {
            display: flex;
            align-items: center;
        }

        .avatar {
            width: 40px;
            height: 40px;
            background-color: #e0e0e0;
            border-radius: 4px;
        }

        .action-buttons button {
            padding: 6px 10px;
            margin-right: 5px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 12px;
        }

        .favorite-button {
            background-color: #ff9800;
            color: white;
        }

        .edit-button {
            background-color: #4caf50;
            color: white;
        }

        .delete-button {
            background-color: #ff5252;
            color: white;
        }

        .love-button {
            background-color: #8f71cb;
            color: white;
        }

        .chart {
            height: 500px;
        }

        .carousel {
            aspect-ratio: 16/9;
        }

        .carousel img {
            object-fit: cover;
            object-position: center top;
            transform: scale(0.95);
            transition: transform 0.3s ease;
        }

        .carousel img.active {
            transform: scale(1);
        }

        span {
            margin-right: 10px;
        }

        /* 下拉菜单样式 */
        .admin-dropdown {
            position: relative;
            display: inline-block;
            cursor: pointer;
        }

        .admin-icon {
            width: 30px;
            height: 30px;
            border-radius: 50%;
            margin-right: 5px;
            vertical-align: middle;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 120px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
            right: 0;
        }
        .dropdown-content input{
            display: inline-block;
        }

        .admin-dropdown:hover .dropdown-content {
            display: block;
        }

        .dropdown-content {
            border-radius: 5px;
        }

        /* 表单样式调整 */
        form {
            display: inline-block;
        }

        input[type="button"] {
            cursor: pointer;
            padding: 8px 15px;
            border: none;
            border-radius: 4px;
            font-size: 14px;
            background-color: #f5f5f5;
            transition: background-color 0.3s;
        }

        input[type="button"]:hover {
            background-color: #e0e0e0;
        }

        .logout-form {
            display: block;
            width: 100%;
            height: 100%;
            text-align: left;
        }

        .logout-form input {
            width: 100%;
            height: 100%;
            padding: 10px;
            background-color: #f5f5f5;
            border-radius: 5px;
            text-align: center;
            border: none;
            cursor: pointer;
        }

        .logout-form input:hover {
            background-color: #e0e0e0;
            height: 100%;
            border-radius: 5px;
        }

        /* 新的模态框样式 */
        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 100;
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: white;
            border-radius: 5px;
            width: 500px;
            max-width: 90%;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            overflow: hidden;
        }

        .modal-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 20px;
            background-color: #f9f9f9;
            border-bottom: 1px solid #eee;
        }

        .modal-title {
            font-size: 18px;
            font-weight: bold;
            color: #333;
        }

        .close-button {
            font-size: 24px;
            font-weight: bold;
            color: #aaa;
            cursor: pointer;
            transition: color 0.3s;
        }

        .close-button:hover {
            color: #333;
        }

        .modal-body {
            padding: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-label {
            display: block;
            margin-bottom: 5px;
            font-size: 14px;
            color: #555;
        }

        .form-input {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        .form-select {
            width: 100%;
            padding: 8px 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 14px;
        }

        .modal-footer {
            display: flex;
            justify-content: flex-end;
            padding: 15px 20px;
            border-top: 1px solid #eee;
        }

        .modal-button {
            padding: 8px 15px;
            margin-left: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }

        .cancel-button {
            background-color: #ccc;
            color: white;
        }

        .confirm-button {
            background-color: #2196F3;
            color: white;
        }

        .edit-button {
            background-color: #4caf50;
            color: white;
        }
    </style>
</head>
<body>
<div class="header">
    <div class="logo">
        <h1>音乐后台管理系统</h1>
    </div>
    <!-- 账户 -->
    <div class="admin-dropdown">
        <img src="./assets/images/admin.png" alt="Admin" class="admin-icon">
        <span>admin</span>
        <div class="dropdown-content">
            <form class="logout-form" action="login.html" method="post">
                <input type="submit" value="退出登录">
            </form>
        </div>
    </div>
</div>

<div class="main-container">
    <!-- 头部 -->
    <div class="sidebar">
        <ul class="sidebar-menu">
            <li class="sidebar-menu-item active" data-page="dashboard">
                <span class="sidebar-icon">🏠</span>
                系统首页
            </li>
            <li class="sidebar-menu-item" data-page="users">
                <span class="sidebar-icon">👤</span>
                用户管理
            </li>
            <li class="sidebar-menu-item" data-page="artists">
                <span class="sidebar-icon">🎤</span>
                歌手管理
            </li>
            <li class="sidebar-menu-item" data-page="playlists">
                <span class="sidebar-icon">🎵</span>
                歌曲管理
            </li>
        </ul>
    </div>
    <!-- 内容 -->
    <div class="content">
        <div id="dashboard" class="page active">
            <h2 class="page-title">系统首页</h2>
            <!-- 数据统计 -->
            <div class="chart1">
                <div class="data-cards">
                    <div class="data-card">
                        <div class="data-number" style="color: #ff5252;">${users.size()}</div>
                        <div class="data-label" style="color: #ff5252;">用户总数</div>
                    </div>
                    <div class="data-card">
                        <div class="data-number" style="color: #4caf50;">${singers.size()}</div>
                        <div class="data-label" style="color: #4caf50;">歌手总数</div>
                    </div>
                    <div class="data-card">
                        <div class="data-number" style="color: #2196f3;">${VipSong.size()}</div>
                        <div class="data-label" style="color: #2196f3;">VIP数量</div>
                    </div>
                    <div class="data-card">
                        <div class="data-number" style="color: #ffc107;">${MusicSongs.size()}</div>
                        <div class="data-label" style="color: #ffc107;">歌曲数量</div>
                    </div>
                </div>
            </div>
            <!-- 轮播图 -->
            <div class="chart2">
                <div class="carousel">
                    <img src="./assets/images/tf.jpg" alt="TF" class="active">
                    <img src="./assets/images/GEM.jpg" alt="GEM">
                    <img src="./assets/images/hzhf.jpg" alt="hzhf">
                    <img src="./assets/images/JayChou.jpg" alt="Jay Chou">
                    <img src="./assets/images/wsl.jpg" alt="wsl">

                    <button class="carousel-control carousel-prev">❮</button>
                    <button class="carousel-control carousel-next">❯</button>

                    <div class="carousel-indicators">
                        <div class="indicator active" data-index="0"></div>
                        <div class="indicator" data-index="1"></div>
                        <div class="indicator" data-index="2"></div>
                        <div class="indicator" data-index="3"></div>
                        <div class="indicator" data-index="4"></div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 用户管理 -->
        <div id="users" class="page">
            <h2 class="page-title">用户管理</h2>
            <div class="user-management">
                <div class="search-bar">
                    <input type="text" class="search-input" placeholder="筛选相关用户">
                </div>
                <div class="button-group">
                    <form class="batch-delete-form">
                        <input type="button" class="batch-delete-button" value="批量删除">
                    </form>
                    <form class="add-user-form">
                        <input type="button" class="add-user-button" value="添加用户" id="openAddModal">
                    </form>
                </div>
                <!-- 用户列表 -->
                <table class="user-table">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>ID</th>
                        <th>密码</th>
                        <th>手机号</th>
                        <th>性别</th>
                        <th>是否为VIP</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>user</td>
                        <td>ID</td>
                        <td>密码</td>
                        <td>18309799999</td>
                        <td>男</td>
                        <td>是</td>
                        <td class="action-buttons">
                            <button class="favorite-button">➕ 增加</button>
                            <button class="edit-button" data-userid="1" data-username="user" data-phone="18309799999" data-gender="男" data-vip="是">✏️ 编辑</button>
                            <button class="delete-button">🗑️ 删除</button>
                            <button class="love-button">❤️ 收藏歌单</button>
                        </td>
                    </tr>
                    <tr>
                        <td>user2</td>
                        <td>ID</td>
                        <td>密码</td>
                        <td>18309799999</td>
                        <td>女</td>
                        <td>否</td>
                        <td class="action-buttons">
                            <button class="favorite-button">➕ 增加</button>
                            <button class="edit-button" data-userid="2" data-username="user2" data-phone="18309799999" data-gender="女" data-vip="否">✏️ 编辑</button>
                            <button class="delete-button">🗑️ 删除</button>
                            <button class="love-button">❤️ 收藏歌单</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- 歌手管理 -->
        <div id="artists" class="page">
            <h2 class="page-title">歌手管理</h2>
            <div class="chart">
                <!-- 歌手列表占位 -->
            </div>
        </div>

        <!-- 歌曲管理 -->
        <div id="playlists" class="page">
            <h2 class="page-title">歌曲管理</h2>
            <div class="chart">
                <!-- 歌单列表占位 -->
            </div>
        </div>
    </div>
</div>

<!-- 添加用户弹窗 -->
<div class="modal" id="addUserModal">
    <form action="${pageContext.request.contextPath}/addUser" method="post">
        <div class="modal-content">
            <div class="modal-header">
                <h2 class="modal-title">添加用户</h2>
                <span class="close-button" id="closeAddModal">&times;</span>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label for="username" class="form-label" >用户名</label>
                    <input type="text" id="username" class="form-input" placeholder="请输入用户名" name="addUserName">
                </div>
                <div class="form-group">
                    <label for="password" class="form-label" >密码</label>
                    <input type="text" id="password" class="form-input" placeholder="请输入密码" name="addPassWord">
                </div>
                <div class="form-group">
                    <label for="phone" class="form-label">手机号</label>
                    <input type="text" id="phone" class="form-input" placeholder="请输入手机号" name="addPhoneNumber">
                </div>
                <div class="form-group">
                    <label for="gender" class="form-label">性别</label>
                    <select id="gender" class="form-select" name="addGender">
                        <option value="">请选择</option>
                        <option value="addMale" >男</option>
                        <option value="addFemale">女</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="is_vip" class="form-label">是否为VIP</label>
                    <select id="is_vip" class="form-select" name="addVip">
                        <option value="">请选择</option>
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button class="modal-button cancel-button" id="cancelAddUser">取消</button>
                <input type="submit" value="确认">
<%--                <button class="modal-button confirm-button" id="confirmAddUser" type="submit">确认</button>--%>
            </div>
        </div>
    </form>

</div>

<!-- 编辑用户弹窗 -->
<div class="modal" id="editUserModal">
    <div class="modal-content">
        <div class="modal-header">
            <h2 class="modal-title">编辑用户</h2>
            <span class="close-button" id="closeEditModal">&times;</span>
        </div>
        <div class="modal-body">
            <input type="hidden" id="editUserid">
            <div class="form-group">
                <label for="editUsername" class="form-label">用户名</label>
                <input type="text" id="editUsername" class="form-input" placeholder="请输入用户名">
            </div>
            <div class="form-group">
                <label for="editPassword" class="form-label">密码</label>
                <input type="text" id="editPassword" class="form-input" placeholder="请输入密码">
            </div>
            <div class="form-group">
                <label for="editPhone" class="form-label">手机号</label>
                <input type="text" id="editPhone" class="form-input" placeholder="请输入手机号">
            </div>
            <div class="form-group">
                <label for="editGender" class="form-label">性别</label>
                <select id="editGender" class="form-select">
                    <option value="">请选择</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select>
            </div>
            <div class="form-group">
                <label for="editIsVip" class="form-label">是否为VIP</label>
                <select id="editIsVip" class="form-select">
                    <option value="">请选择</option>
                    <option value="是">是</option>
                    <option value="否">否</option>
                </select>
            </div>
        </div>
        <div class="modal-footer">
            <button class="modal-button cancel-button" id="cancelEditUser">取消</button>
            <button class="modal-button confirm-button" id="confirmEditUser">确认</button>
        </div>
    </div>
</div>

<!-- 收藏歌单弹窗 -->
<div class="modal" id="favoritePlaylistModal">
    <div class="modal-content">
        <div class="modal-header">
            <h2 class="modal-title">收藏歌单</h2>
            <span class="close-button" id="closeFavoriteModal">&times;</span>
        </div>
        <div class="modal-body">
            <!-- 这里可以添加收藏歌单相关的表单或内容 -->
        </div>
        <div class="modal-footer">
            <button class="modal-button cancel-button" id="cancelFavorite">取消</button>
            <button class="modal-button confirm-button" id="confirmFavorite">确认</button>
        </div>
    </div>
</div>
<script>

    // 页面切换
    document.addEventListener('DOMContentLoaded', function () {
        const menuItems = document.querySelectorAll('.sidebar-menu-item');

        menuItems.forEach(item => {
            item.addEventListener('click', function () {
                // 移除所有菜单项的 active 类
                menuItems.forEach(i => i.classList.remove('active'));

                // 为当前点击的菜单项添加 active 类
                this.classList.add('active');

                // 获取对应页面的 ID
                const pageId = this.getAttribute('data-page');

                // 隐藏所有页面
                document.querySelectorAll('.page').forEach(page => {
                    page.classList.remove('active');
                });

                // 显示对应页面
                document.getElementById(pageId).classList.add('active');
            });
        });

        // 轮播图
        const carousel = document.querySelector('.carousel');
        const images = carousel.querySelectorAll('img');
        const indicators = carousel.querySelectorAll('.indicator');
        const prevBtn = carousel.querySelector('.carousel-prev');
        const nextBtn = carousel.querySelector('.carousel-next');
        let currentIndex = 0;

        function showImage(index) {
            // 隐藏所有图片和指示器
            images.forEach(img => img.classList.remove('active'));
            indicators.forEach(ind => ind.classList.remove('active'));

            // 显示当前图片和指示器
            images[index].classList.add('active');
            indicators[index].classList.add('active');

            currentIndex = index;
        }

        function nextImage() {
            let nextIndex = currentIndex + 1;
            if (nextIndex >= images.length) {
                nextIndex = 0;
            }
            showImage(nextIndex);
        }

        function prevImage() {
            let prevIndex = currentIndex - 1;
            if (prevIndex < 0) {
                prevIndex = images.length - 1;
            }
            showImage(prevIndex);
        }

        // 设置按钮点击事件
        nextBtn.addEventListener('click', nextImage);
        prevBtn.addEventListener('click', prevImage);

        // 设置指示器点击事件
        indicators.forEach((indicator, index) => {
            indicator.addEventListener('click', () => {
                showImage(index);
            });
        });

        // 自动轮播
        setInterval(nextImage, 3000);

        // 模态框功能
        const addModal = document.getElementById('addUserModal');
        const editModal = document.getElementById('editUserModal');
        const favoriteModal = document.getElementById('favoritePlaylistModal');

        const openAddModalButton = document.getElementById('openAddModal');
        const closeAddModalButton = document.getElementById('closeAddModal');
        const cancelAddUserButton = document.getElementById('cancelAddUser');
        const confirmAddUserButton = document.getElementById('confirmAddUser');

        const closeEditModalButton = document.getElementById('closeEditModal');
        const cancelEditUserButton = document.getElementById('cancelEditUser');
        const confirmEditUserButton = document.getElementById('confirmEditUser');

        const closeFavoriteModalButton = document.getElementById('closeFavoriteModal');
        const cancelFavoriteButton = document.getElementById('cancelFavorite');
        const confirmFavoriteButton = document.getElementById('confirmFavorite');

        // 打开添加用户模态框
        openAddModalButton.addEventListener('click', function() {
            addModal.style.display = 'flex';
        });

        // 关闭添加用户模态框
        function closeAddModal() {
            addModal.style.display = 'none';
        }

        closeAddModalButton.addEventListener('click', closeAddModal);
        cancelAddUserButton.addEventListener('click', closeAddModal);

        // 确认添加用户
        confirmAddUserButton.addEventListener('click', function() {
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            const phone = document.getElementById('phone').value;
            const gender = document.getElementById('gender').value;
            const is_vip = document.getElementById('is_vip').value;

            // 这里可以添加提交表单的逻辑
            // alert('添加用户成功！');

            closeAddModal();

            // 清空表单
            document.getElementById('username').value = '';
            document.getElementById('password').value = '';
            document.getElementById('phone').value = '';
            document.getElementById('gender').value = '';
            document.getElementById('is_vip').value = '';
        });

        // 编辑用户功能
        const editButtons = document.querySelectorAll('.edit-button');
        editButtons.forEach(button => {
            button.addEventListener('click', function() {
                const userid = this.getAttribute('data-userid');
                const username = this.getAttribute('data-username');
                const phone = this.getAttribute('data-phone');
                const gender = this.getAttribute('data-gender');
                const is_vip = this.getAttribute('data-vip');

                // 填充编辑表单
                document.getElementById('editUserid').value = userid;
                document.getElementById('editUsername').value = username;
                document.getElementById('editPassword').value = '密码'; // 保留原有值
                document.getElementById('editPhone').value = phone;

                // 设置性别下拉框
                const genderSelect = document.getElementById('editGender');
                for (let i = 0; i < genderSelect.options.length; i++) {
                    if (genderSelect.options[i].value === gender) {
                        genderSelect.selectedIndex = i;
                        break;
                    }
                }

                // 设置VIP下拉框
                const vipSelect = document.getElementById('editIsVip');
                for (let i = 0; i < vipSelect.options.length; i++) {
                    if (vipSelect.options[i].value === is_vip) {
                        vipSelect.selectedIndex = i;
                        break;
                    }
                }

                // 打开编辑模态框
                editModal.style.display = 'flex';
            });
        });

        // 关闭编辑用户模态框
        function closeEditModal() {
            editModal.style.display = 'none';
        }

        closeEditModalButton.addEventListener('click', closeEditModal);
        cancelEditUserButton.addEventListener('click', closeEditModal);

        // 确认编辑用户
        confirmEditUserButton.addEventListener('click', function() {
            const userid = document.getElementById('editUserid').value;
            const username = document.getElementById('editUsername').value;
            const password = document.getElementById('editPassword').value;
            const phone = document.getElementById('editPhone').value;
            const gender = document.getElementById('editGender').value;
            const is_vip = document.getElementById('editIsVip').value;

            // 这里可以添加提交表单的逻辑
            alert('编辑用户成功！');
            closeEditModal();
        });

        // 收藏歌单功能
        const loveButtons = document.querySelectorAll('.love-button');
        loveButtons.forEach(button => {
            button.addEventListener('click', function() {
                favoriteModal.style.display = 'flex';
            });
        });

        // 关闭收藏歌单模态框
        function closeFavoriteModal() {
            favoriteModal.style.display = 'none';
        }

        closeFavoriteModalButton.addEventListener('click', closeFavoriteModal);
        cancelFavoriteButton.addEventListener('click', closeFavoriteModal);

        // 确认收藏歌单
        confirmFavoriteButton.addEventListener('click', function() {
            // 这里可以添加收藏歌单的逻辑
            alert('收藏歌单成功！');
            closeFavoriteModal();
        });

        // 点击模态框外部关闭模态框
        window.addEventListener('click', function(event) {
            if (event.target === addModal) {
                closeAddModal();
            }
            if (event.target === editModal) {
                closeEditModal();
            }
            if (event.target === favoriteModal) {
                closeFavoriteModal();
            }
        });
    });
</script>
</body>
</html>
