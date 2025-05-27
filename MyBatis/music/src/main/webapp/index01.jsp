<%--
  Created by IntelliJ IDEA.
  User: 29298
  Date: 2025/5/19
  Time: 下午7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>音乐管理系统</title>
    <link rel="stylesheet" href="indexCss.css">
</head>
<body>
<div class="header">
    <div class="logo">
        <h1>歌单管理系统</h1>
    </div>
    <div class="admin-info">
        <div class="admin-avatar">
            <img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mP8/x8AAwMCAO+ip1sAAAAASUVORK5CYII="
                    alt="Admin Avatar">
        </div>
        <span>admin</span>
    </div>
</div>

<div class="main-container">
    <div class="sidebar">
        <ul class="sidebar-menu">
            <li class="sidebar-menu-item active" data-page="dashboard">
                <span class="sidebar-icon">♪</span>
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

    <div class="content">
        <div id="dashboard" class="page active">
            <h2 class="page-title">系统首页</h2>
            <div class="chart">
                <!-- 数据统计 -->
                <div class="data-cards">
                    <div class="data-card">
                        <div class="data-number" style="color: #ff5252;">3</div>
                        <div class="data-label" style="color: #ff5252;">用户总数</div>
                    </div>
                    <div class="data-card">
                        <div class="data-number" style="color: #4caf50;">2</div>
                        <div class="data-label" style="color: #4caf50;">歌手总数</div>
                    </div>
                    <div class="data-card">
                        <div class="data-number" style="color: #2196f3;">1</div>
                        <div class="data-label" style="color: #2196f3;">VIP数量</div>
                    </div>
                    <div class="data-card">
                        <div class="data-number" style="color: #ffc107;">5</div>
                        <div class="data-label" style="color: #ffc107;">歌曲数量</div>
                    </div>
                </div>
            </div>
            <div class="chart">
                <!-- 轮播图 -->
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

        <div id="users" class="page">
            <h2 class="page-title">用户管理</h2>
            <div class="user-management">
                <div class="search-bar">
                    <input type="text" class="search-input" placeholder="筛选相关用户">
                </div>
                <div class="button-group">
                    <button class="batch-delete-button">
                        <span class="button-icon">🗑️</span>
                        批量删除
                    </button>
                    <button class="add-user-button">
                        <span class="button-icon">➕</span>
                        添加用户
                    </button>
                </div>
                <table class="user-table">
                    <thead>
                    <tr>
                        <th>用户图片</th>
                        <th>用户名</th>
                        <th>性别</th>
                        <th>手机号</th>
                        <th>电子邮箱</th>
                        <th>生日</th>
                        <th>签名</th>
                        <th>地区</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="user-avatar">
                            <div class="avatar"></div>
                        </td>
                        <td>user</td>
                        <td>男</td>
                        <td>18677772222</td>
                        <td>1@qq.com</td>
                        <td>2023-11-26</td>
                        <td>一个厉害的歌手</td>
                        <td>背景</td>
                        <td class="action-buttons">
                            <button class="favorite-button">⭐ 收藏</button>
                            <button class="edit-button">✏️ 编辑</button>
                            <button class="delete-button">🗑️ 删除</button>
                        </td>
                    </tr>
                    <tr>
                        <td class="user-avatar">
                            <div class="avatar"></div>
                        </td>
                        <td>user2</td>
                        <td>男</td>
                        <td>1866665555</td>
                        <td>2@qq.com</td>
                        <td>2023-11-26</td>
                        <td>一个非常优秀的歌手</td>
                        <td>杭州</td>
                        <td class="action-buttons">
                            <button class="favorite-button">⭐ 收藏</button>
                            <button class="edit-button">✏️ 编辑</button>
                            <button class="delete-button">🗑️ 删除</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div id="artists" class="page">
            <h2 class="page-title">歌手管理</h2>
            <div class="chart">
                <!-- 歌手列表占位 -->
            </div>
        </div>

        <div id="playlists" class="page">
            <h2 class="page-title">歌曲管理</h2>
            <div class="chart">
                <!-- 歌单列表占位 -->
            </div>
        </div>
    </div>
</div>

<script>
    //页面切换
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
    });
</script>
</body>
</html>
