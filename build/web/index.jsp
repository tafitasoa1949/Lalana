<%@page import="models.Priorite"%>
<%@page import="models.Route"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Route[] list_route = (Route[]) request.getAttribute("list_route"); %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Components / Accordion - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin - v2.2.2
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

   <jsp:include page="content/sidebar.jsp" />

  <main id="main" class="main">

    <div class="pagetitle">
      <h1></h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a >Lalana</a></li>
          <li class="breadcrumb-item">Detruit</li>
          <li class="breadcrumb-item active">Ajouter</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
        <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Inserer detruit</h5>
              <form action="<%= request.getContextPath() %>/SplitServlet" method="get">
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">Route Nationale</label>
                        <div class="col-sm-10">
                            <select class="form-select" aria-label="Default select example" name="routeId">
                                <% for (int i = 0; i < list_route.length; i++) { %>
                                    <option value="<%= list_route[i].getId() %>"><%= list_route[i].getNom() %></option>
                                <% } %>
                          </select>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">Date</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" name="date">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputText" class="col-sm-1 col-form-label">PK Debut</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" name="pkDebutSplit">
                        </div>
                        <label for="inputText" class="col-sm-1 col-form-label">PK Fin</label>
                        <div class="col-sm-2">
                            <input type="text"  class="form-control" name="pkFinSplit">
                        </div>
                        <label for="inputText" class="col-sm-1 col-form-label">Etat</label>
                        <div class="col-sm-2">
                            <input type="text"  class="form-control" name="etatSplit">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-12">
                          <button type="submit" class="btn btn-primary">Ajouter</button>
                        </div>
                    </div>
              </form>
            </div>
          </div>

        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Inserer detruit</h5>
              <form action="<%= request.getContextPath() %>/DetruitServlet" method="get">
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">Route Nationale</label>
                        <div class="col-sm-10">
                            <select class="form-select" aria-label="Default select example" name="routeId">
                                <% for (int i = 0; i < list_route.length; i++) { %>
                                    <option value="<%= list_route[i].getId() %>"><%= list_route[i].getNom() %></option>
                                <% } %>
                          </select>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">Date</label>
                        <div class="col-sm-10">
                            <input type="date" class="form-control" name="date">
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label for="inputText" class="col-sm-1 col-form-label">PK Debut</label>
                        <div class="col-sm-2">
                            <input type="number" step="any" class="form-control" name="pkDebut[]">
                        </div>
                        <label for="inputText" class="col-sm-1 col-form-label">PK Fin</label>
                        <div class="col-sm-2">
                            <input type="number" step="any" class="form-control" name="pkFin[]">
                        </div>
                        <label for="inputText" class="col-sm-1 col-form-label">Etat</label>
                        <div class="col-sm-2">
                            <input type="number" step="any" class="form-control" name="etat[]">
                        </div>
                        <div class="col-sm-3">
                            <button type="button" class="btn btn-success" onclick="ajouterChamp()"><i class="bi bi-check-circle"></i></button>
                        </div>
                    </div>
                  <div id="plus">
                      
                  </div>
                    
                    <div class="row mb-3">
                        <div class="col-sm-12">
                          <button type="submit" class="btn btn-primary">Ajouter</button>
                        </div>
                    </div>
              </form>
            </div>
          </div>

        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
                <h5 class="card-title">Voir list</h5>
                <form action="<%= request.getContextPath() %>/ListServlet"" method="get">
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">Route Nationale</label>
                        <div class="col-sm-10">
                            <select class="form-select" aria-label="Default select example" name="routeId">
                                <% for (int i = 0; i < list_route.length; i++) { %>
                                    <option value="<%= list_route[i].getId() %>"><%= list_route[i].getNom() %></option>
                                <% } %>
                          </select>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-sm-12">
                          <button type="submit" class="btn btn-success">Voir</button>
                        </div>
                    </div>
                </form>
            </div>
          </div>
        </div>
      </div>
    </section>

  </main><!-- End #main -->


  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.min.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
  <script>
      function ajouterChamp() {
         const divplus = document.getElementById("plus");

        // Create a new row
        const divGM = document.createElement('div');
        divGM.classList.add('row', 'mb-3');

        // Label for "PK Debut"
        const labelPKDebut = document.createElement('label');
        labelPKDebut.classList.add('col-sm-1', 'col-form-label');
        labelPKDebut.textContent = 'PK Debut';
        divGM.appendChild(labelPKDebut);

        // Input for "PK Debut"
        const divPKDebut = document.createElement('div');
        divPKDebut.classList.add('col-sm-2');
        const inputPKDebut = document.createElement('input');
        inputPKDebut.type = 'number';
        inputPKDebut.step = 'any';
        inputPKDebut.classList.add('form-control');
        inputPKDebut.name = 'pkDebut[]';
        divPKDebut.appendChild(inputPKDebut);
        divGM.appendChild(divPKDebut);
        
        // Label for "PK Fin"
        const labelPKFin = document.createElement('label');
        labelPKFin.classList.add('col-sm-1', 'col-form-label');
        labelPKFin.textContent = 'PK Fin';
        divGM.appendChild(labelPKFin);

        // Input for "PK Fin"
        const divPKFin = document.createElement('div');
        divPKFin.classList.add('col-sm-2');
        const inputPKFin = document.createElement('input');
        inputPKFin.type = 'number';
        inputPKFin.step = 'any';
        inputPKFin.classList.add('form-control');
        inputPKFin.name = 'pkFin[]';
        divPKFin.appendChild(inputPKFin);
        divGM.appendChild(divPKFin);
        
         // Label for "Etat"
        const labelEtat = document.createElement('label');
        labelEtat.classList.add('col-sm-1', 'col-form-label');
        labelEtat.textContent = 'Etat';
        divGM.appendChild(labelEtat);

        // Input for "Etat"
        const divEtat = document.createElement('div');
        divEtat.classList.add('col-sm-2');
        const inputEtat = document.createElement('input');
        inputEtat.type = 'number';
        inputEtat.step = 'any';
        inputEtat.classList.add('form-control');
        inputEtat.name = 'etat[]';
        divEtat.appendChild(inputEtat);
        divGM.appendChild(divEtat);
        
        // Append the new row to the container
        divplus.appendChild(divGM);
        
    }
  </script>
        
</body>

</html>