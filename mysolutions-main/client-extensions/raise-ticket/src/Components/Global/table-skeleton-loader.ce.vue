<template>
  <div class="container-fluid my-3 mnh-375p">
    <div class="row">
      <div class="col-md-12">
        <div class="table-responsive">
          <table class="table">
            <thead></thead>
            <tbody>
              <tr v-for="i in 12">
                <td><div class="skeleton skeleton-text skeleton-25"></div></td>
                <td><div class="skeleton skeleton-text skeleton-45"></div></td>
                <td>
                  <div class="skeleton skeleton-text skeleton-40"></div>
                </td>
                <td><div class="skeleton skeleton-text skeleton-15"></div></td>
                <td><div class="skeleton skeleton-text skeleton-15"></div></td>
                <td><div class="skeleton skeleton-text skeleton-25"></div></td>
                <td><div class="skeleton skeleton-text skeleton-25"></div></td>
                <td><div class="skeleton skeleton-text skeleton-20"></div></td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CompoentLoader",
};
</script>