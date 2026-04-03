document.addEventListener('DOMContentLoaded', function() {
    const menuToggle = document.getElementById('menuToggle');
    const nav = document.getElementById('nav');
    
    if (menuToggle && nav) {
        menuToggle.addEventListener('click', function() {
            nav.classList.toggle('active');
        });
    }
    
    const tabBtns = document.querySelectorAll('.tab-btn');
    const tabContents = document.querySelectorAll('.tab-content');
    
    tabBtns.forEach(btn => {
        btn.addEventListener('click', function() {
            const tabId = this.getAttribute('data-tab');
            
            tabBtns.forEach(b => b.classList.remove('active'));
            tabContents.forEach(c => c.classList.remove('active'));
            
            this.classList.add('active');
            document.getElementById(tabId).classList.add('active');
        });
    });
    
    checkLoginStatus();
});

function showLoginModal() {
    const modal = document.getElementById('loginModal');
    if (modal) {
        modal.style.display = 'flex';
    }
}

function closeLoginModal() {
    const modal = document.getElementById('loginModal');
    if (modal) {
        modal.style.display = 'none';
    }
    // 恢复为登录模式
    isLoginMode = true;
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const modalTitle = document.getElementById('modalTitle');
    if (loginForm) loginForm.style.display = 'block';
    if (registerForm) registerForm.style.display = 'none';
    if (modalTitle) modalTitle.textContent = '用户登录';
}

let isLoginMode = true;

function toggleAuthMode(event) {
    if (event) event.preventDefault();
    isLoginMode = !isLoginMode;
    const loginForm = document.getElementById('loginForm');
    const registerForm = document.getElementById('registerForm');
    const modalTitle = document.getElementById('modalTitle');
    
    if (isLoginMode) {
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
        if (modalTitle) modalTitle.textContent = '用户登录';
    } else {
        loginForm.style.display = 'none';
        registerForm.style.display = 'block';
        if (modalTitle) modalTitle.textContent = '用户注册';
    }
}

async function handleLogin(event) {
    event.preventDefault();
    
    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;
    
    if (username && password) {
        try {
            const response = await fetch('/api/users/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            });
            
            const data = await response.json();
            
            if (response.ok && data.success) {
                const user = data.user;
                localStorage.setItem('currentUser', JSON.stringify(user));
                closeLoginModal();
                updateUI();
                showAlert('登录成功！欢迎，' + (user.realName || user.username), 'success');
            } else {
                showAlert(data.message || '登录失败', 'error');
            }
        } catch (error) {
            console.error('Login error:', error);
            showAlert('网络错误，请稍后再试', 'error');
        }
    }
}

async function handleRegister(event) {
    event.preventDefault();
    
    const username = document.getElementById('regUsername').value;
    const password = document.getElementById('regPassword').value;
    const realName = document.getElementById('regRealName').value;
    const roleRadios = document.getElementsByName('regRole');
    let role = 'student';
    for (const radio of roleRadios) {
        if (radio.checked) {
            role = radio.value;
            break;
        }
    }
    
    if (username && password) {
        try {
            const response = await fetch('/api/users', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password, realName, role })
            });
            
            if (response.ok) {
                const user = await response.json();
                localStorage.setItem('currentUser', JSON.stringify(user));
                closeLoginModal();
                updateUI();
                showAlert('注册成功！欢迎，' + (user.realName || user.username), 'success');
            } else {
                showAlert('注册失败，用户名可能已存在', 'error');
            }
        } catch (error) {
            console.error('Register error:', error);
            showAlert('网络错误，请稍后再试', 'error');
        }
    }
}

function logout() {
    localStorage.removeItem('currentUser');
    updateUI();
    showAlert('已退出登录', 'info');
}

function checkLoginStatus() {
    updateUI();
}

function updateUI() {
    const user = JSON.parse(localStorage.getItem('currentUser'));
    const loginBtn = document.getElementById('loginBtn');
    const userInfo = document.getElementById('userInfo');
    const userName = document.getElementById('userName');
    const teacherNavItem = document.getElementById('teacherNavItem');
    
    if (user) {
        if (loginBtn) loginBtn.style.display = 'none';
        if (userInfo) userInfo.style.display = 'flex';
        if (userName) userName.textContent = user.username;
        
        if (user.role === 'teacher' && teacherNavItem) {
            teacherNavItem.style.display = 'list-item';
        } else if (teacherNavItem) {
            teacherNavItem.style.display = 'none';
        }
    } else {
        if (loginBtn) loginBtn.style.display = 'block';
        if (userInfo) userInfo.style.display = 'none';
        if (teacherNavItem) teacherNavItem.style.display = 'none';
    }
}

function showAlert(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.style.cssText = `
        position: fixed;
        top: 100px;
        right: 20px;
        padding: 15px 25px;
        background: ${type === 'success' ? '#4CAF50' : type === 'error' ? '#f44336' : '#2196F3'};
        color: white;
        border-radius: 8px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        z-index: 9999;
        animation: slideIn 0.3s ease;
    `;
    alertDiv.textContent = message;
    
    const style = document.createElement('style');
    style.textContent = `
        @keyframes slideIn {
            from { transform: translateX(400px); opacity: 0; }
            to { transform: translateX(0); opacity: 1; }
        }
    `;
    document.head.appendChild(style);
    document.body.appendChild(alertDiv);
    
    setTimeout(() => {
        alertDiv.remove();
    }, 3000);
}
