<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Mail Settings</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

<div class="card shadow-lg rounded">
    <div class="card-header bg-primary text-white">
        <h3 class="mb-0">📧 Mailbox Settings</h3>
    </div>
    <div class="card-body">
        <form:form method="post" action="settings/update" modelAttribute="settings">
            <div class="mb-3">
                <label class="form-label fw-bold">Language:</label>
                <form:select path="language" cssClass="form-select">
                    <form:options items="${languages}" />
                </form:select>
            </div>

            <div class="mb-3">
                <label class="form-label fw-bold">Page Size:</label>
                <div class="input-group">
                    <span class="input-group-text">Show</span>
                    <form:select path="pageSize" cssClass="form-select">
                        <form:options items="${pageSizes}" />
                    </form:select>
                    <span class="input-group-text">emails per page</span>
                </div>
            </div>

            <div class="mb-3 form-check">
                <form:checkbox path="spamFilter" cssClass="form-check-input" id="spamFilter"/>
                <label class="form-check-label" for="spamFilter">Enable spam filter</label>
            </div>

            <div class="mb-3">
                <label class="form-label fw-bold">Signature:</label>
                <form:textarea path="signature" rows="4" cssClass="form-control"/>
            </div>

            <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success">
                    Update
                </button>
                <a href="/settings" class="btn btn-secondary">
                    Cancel
                </a>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
