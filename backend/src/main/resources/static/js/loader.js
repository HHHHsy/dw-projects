(function() {
    function getBasePath() {
        var path = window.location.pathname;
        if (path.includes('/pages/')) {
            return '../';
        }
        return './';
    }

    function getCurrentPage() {
        var path = window.location.pathname;
        var filename = path.substring(path.lastIndexOf('/') + 1);
        if (!filename || filename === '') {
            filename = 'index.html';
        }
        return filename;
    }

    function loadHeader() {
        var basePath = getBasePath();
        var currentPage = getCurrentPage();

        var xhr = new XMLHttpRequest();
        xhr.open('GET', basePath + 'components/header.html', true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var headerContent = xhr.responseText;
                
                headerContent = headerContent.replace(/href="([^"]+)"/g, function(match, href) {
                    if (href.startsWith('pages/') && basePath === '../') {
                        return 'href="' + href.substring(6) + '"';
                    }
                    if (href.endsWith('.html') && basePath === './' && !href.startsWith('pages/') && href !== 'index.html') {
                        return 'href="pages/' + href + '"';
                    }
                    if (href === 'index.html' && basePath === '../') {
                        return 'href="../index.html"';
                    }
                    return match;
                });

                var tempDiv = document.createElement('div');
                tempDiv.innerHTML = headerContent;
                var links = tempDiv.querySelectorAll('nav a');
                links.forEach(function(link) {
                    var href = link.getAttribute('href');
                    var linkPage = href.substring(href.lastIndexOf('/') + 1);
                    if (linkPage === currentPage) {
                        link.classList.add('active');
                    }
                });

                var bodyHTML = document.body.innerHTML;
                var startMarker = '<!-- DREAMWEAVER_PREVIEW_START';
                var endMarker = '<!-- DREAMWEAVER_PREVIEW_END -->';
                
                var startIndex = bodyHTML.indexOf(startMarker);
                var endIndex = bodyHTML.indexOf(endMarker);
                
                if (startIndex !== -1 && endIndex !== -1) {
                    var newBodyHTML = bodyHTML.substring(0, startIndex) + 
                                      tempDiv.innerHTML + 
                                      bodyHTML.substring(endIndex + endMarker.length);
                    document.body.innerHTML = newBodyHTML;
                }

                if (typeof checkLoginStatus === 'function') {
                    checkLoginStatus();
                }
            }
        };
        xhr.send();
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', loadHeader);
    } else {
        loadHeader();
    }
})();
