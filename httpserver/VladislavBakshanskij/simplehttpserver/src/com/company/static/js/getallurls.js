fetch("/api/urls/")
    .then(response => response.json())
    .then(urls => {
        const app = document.getElementById("app");
        let urlHTMLList = '';
        urls.forEach(urlAddress => {
            let responseHeaders = urlAddress.responseHeaders;
            let listHeaders = '';
            Object.keys(responseHeaders).forEach(key => {
                listHeaders += key === 'null' ? `<li>${responseHeaders[key]}</li>` : `<li>${key}: ${responseHeaders[key]}</li>`;
            });

            urlHTMLList += `<li><div><a href="${urlAddress.url}">${urlAddress.url}</a><br/><ul>${listHeaders}</ul></div></li>`;
        });
        app.innerHTML = `<ul>${urlHTMLList}</ul>`;
    });