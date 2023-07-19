rootProject.name = "one-home-pet"
include(
    "module-user-service",
    "module-appointment-service",
    "module-eureka-service",
    "module-generator-lib",
    "module-pet-service"
)
include("module-feign-client")
