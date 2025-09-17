<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Result</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h2 class="mb-0">Updated Settings</h2>
                </div>
                <div class="card-body">
                    <ul class="list-group list-group-flush mb-3">
                        <li class="list-group-item">
                            <strong>Language:</strong> ${settings.language}
                        </li>
                        <li class="list-group-item">
                            <strong>Page Size:</strong> ${settings.pageSize}
                        </li>
                        <li class="list-group-item">
                            <strong>Spam Filter:</strong>
                            <c:choose>
                                <c:when test="${settings.spamFilter}">
                                    Enabled
                                </c:when>
                                <c:otherwise>
                                    Disabled
                                </c:otherwise>
                            </c:choose>
                        </li>
                        <li class="list-group
-item">
                            <strong>Signature:</strong> <br/>
                            <pre class="bg-light p-2 rounded">${settings.signature}</pre>
                        </li>
                    </ul>
                    <a href="/settings" class="btn btn-secondary w-100">Back to Settings
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
