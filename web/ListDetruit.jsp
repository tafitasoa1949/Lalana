<%@page import="models.DetailRoute"%>
<%@page import="models.Route"%>
<%@page import="models.Detruit"%>
<%@page import="models.Priorite"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Detruit[] listDetruit = (Detruit[]) request.getAttribute("listDetruit"); %>
<% Priorite[] listPriorite = (Priorite[]) request.getAttribute("listPriorite"); %>
<% Route route = (Route) request.getAttribute("route"); %>
<% DetailRoute detailRoute = (DetailRoute) request.getAttribute("detailRoute"); %>
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
          <li class="breadcrumb-item">Reparer</li>
          <li class="breadcrumb-item active">List</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
                <h5>Route : <%= route.getNom() %></h5>
                <h5>Ville debut : <%= detailRoute.getVilleDebut().getNom() %></h5>
                <h5>Ville Fin : <%= detailRoute.getVilleFin().getNom() %></h5>
                <h5>Budget : <%= route.getBudget() %> Ar</h5>
                <h5>Longeur : <%= detailRoute.getPkFin()-detailRoute.getPkDebiut() %> Km</h5>
                <%
                    String error = (String)request.getAttribute("error");
                    if (error != null && !error.isEmpty()) {
                %>
                    <div class="alert alert-danger" role="alert">
                        <%= error %>
                    </div>
                <%
                    }
                %>
              <h5 class="card-title">Listes detruits</h5>
              <table class="table">
                <thead>
                  <tr>
                        <th scope="col">Route Nationale</th>
                        <th scope="col">PK debut</th>
                        <th scope="col">PK fin</th>
                        <th scope="col">Etat</th>
                        <th scope="col">Date</th>
                  </tr>
                </thead>
                <tbody>
                   <% for (int i = 0; i < listDetruit.length ; i++) { %>
                    <tr>
                        <td><%= listDetruit[i].getRoute().getNom() %></td>
                        <td><%= listDetruit[i].getPkDebut() %></td>
                        <td><%= listDetruit[i].getPkFin() %></td>
                        <td><%= listDetruit[i].getEtat() %></td>
                        <td><%= listDetruit[i].getDate() %></td>
                    </tr
                    <% } %>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
       <div class="row">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
                <h5 class="card-title">Voir list</h5>
                <form action="<%= request.getContextPath() %>/Reparation"" method="get">
                    <input type="hidden" name="routeId" value="<%= route.getId() %>">
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label"> Priorité </label>
                        <div class="col-sm-10">
                            <select class="form-select" aria-label="Default select example" name="prioriteId">
                                <% for (int i = 0; i < listPriorite.length; i++) { %>
                                    <option value="<%= listPriorite[i].getId() %>"><%= listPriorite[i].getNom() %></option>
                                <% } %>
                          </select>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <label class="col-sm-2 col-form-label">Budget</label>
                        <div class="col-sm-10">
                            <input type="number" step="any" class="form-control" name="budget">
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
        
</body>

</html>