<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Continental Bhansa | Menu</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Menu.css">
</head>
<body>

<%@ include file="navbar.jsp" %>

<h1>Explore Our Continental Menu</h1>

<div class="search-wrapper">
    <form method="get" action="${pageContext.request.contextPath}/MenuController">
        <div class="search-container">
            <input type="text" id="menuSearch" name="search" placeholder="Search dishes..." value="${param.search}" />
            <button type="submit">Search</button>
        </div>

        <div class="filter-container">
            <select id="cuisineFilter" name="cuisine" onchange="this.form.submit()">
                <option value="" ${empty param.cuisine ? 'selected' : ''}>All Cuisines</option>
                <option value="indian" ${param.cuisine == 'indian' ? 'selected' : ''}>Indian</option>
                <option value="chinese" ${param.cuisine == 'chinese' ? 'selected' : ''}>Chinese</option>
                <option value="italian" ${param.cuisine == 'italian' ? 'selected' : ''}>Italian</option>
                <option value="nepali" ${param.cuisine == 'nepali' ? 'selected' : ''}>Nepali</option>
                <option value="desserts" ${param.cuisine == 'desserts' ? 'selected' : ''}>Desserts</option>
            </select>
        </div>
    </form>
</div>

<div class="maroon-section">
    <h2>Indulge in our Signature Dishes</h2>
    <p>Fresh, bold, and crafted with passion — discover the heart of our menu.</p>
</div>

<!-- Filter Tabs -->
<div class="category-tabs">
    <button onclick="scrollToCategory('indian')">Indian</button>
    <button onclick="scrollToCategory('chinese')">Chinese</button>
    <button onclick="scrollToCategory('italian')">Italian</button>
    <button onclick="scrollToCategory('nepali')">Nepali</button>
    <button onclick="scrollToCategory('desserts')">Desserts</button>
</div>

<!-- Check if any menu items were found -->
<c:if test="${empty menuItems}">
    <div class="no-results">
        <h3>No dishes found matching your search criteria</h3>
        <p>Try different keywords or browse our categories below</p>
    </div>
</c:if>

<!-- Display results -->
<c:if test="${not empty menuItems}">
    <!-- Dynamic Sections for Each Category -->
    <c:set var="categories" value="indian,chinese,italian,nepali,desserts" />
    <c:forEach var="category" items="${categories}" varStatus="loop">
        <!-- Check if there are items in this category before creating the section -->
        <c:set var="hasCategoryItems" value="false" />
        <c:forEach var="item" items="${menuItems}">
            <c:if test="${item.category.toLowerCase() eq category}">
                <c:set var="hasCategoryItems" value="true" />
            </c:if>
        </c:forEach>
        
        <!-- Only display category if it has items or no search is active -->
        <c:if test="${hasCategoryItems || (empty param.search && empty param.cuisine)}">
            <section id="${category}">
                <h2 class="category-title">${category.substring(0,1).toUpperCase()}${category.substring(1)} Cuisine</h2>
                <div class="menu-grid">
                    <c:forEach var="item" items="${menuItems}">
                        <c:if test="${item.category.toLowerCase() eq category}">
                            <div class="menu-card">
                                <img src="${pageContext.request.contextPath}/${item.imagePath}" alt="${item.name}">
                                <h3>${item.name}</h3>
                                <p>${item.description}</p>
                                <span>$${item.price}</span>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </section>
        </c:if>
    </c:forEach>
</c:if>

<%@ include file="Footer.jsp" %>

<script>
function scrollToCategory(categoryId) {
    const section = document.getElementById(categoryId);
    if (section) {
        section.scrollIntoView({ behavior: 'smooth' });
    }
}
</script>

</body>
</html>
