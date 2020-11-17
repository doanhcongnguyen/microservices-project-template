<template>
  <div class="app-container">
    <el-card class="card" shadow="hover">
      <b> {{ $t('device.title') }} </b>
      <div class="page-content">
        <Search
          :search-form="searchModel"
          @do-search="search"
        />

        <el-button :disabled="selectedModels.length === 0" type="text" icon="el-icon-remove" class="btn-container danger" @click="handleDelete">{{ $t('global.button.delete') }}</el-button>
        <el-button type="text" icon="el-icon-circle-plus" class="btn-container" @click="handleAdd">{{ $t('global.button.add') }}</el-button>

        <Add
          :dialog-form-visible="dialogFormVisible"
          :is-update-mode="isUpdateMode"
          :form="formModel"
          @close-dialog="closeDialog"
        />

        <List
          :list="list"
          :loading="loading"
          @on-handle-update="handleUpdate"
          @on-handle-select="handleSelect"
          @on-refresh="refreshList"
        />

        <Pagination
          :total="pagination.total"
          :page.sync="pagination.page"
          :limit.sync="pagination.limit"
          @pagination="search"
        />
      </div>
    </el-card>
  </div>
</template>

<script>
import Search from './components/Search'
import List from './components/List'
import Add from './components/Add'
import Pagination from '@/components/Pagination'

import { doDelete, filter } from '@/api/device-management'
import { showSuccessMessage, createConfirmBox } from '@/utils/commons'
import EventBus from '@/config/event-bus'

export default {
  components: {
    Search,
    Add,
    List,
    Pagination
  },
  data() {
    return {
      dialogFormVisible: false,
      isUpdateMode: false,
      formModel: {},
      searchModel: {},
      selectedModels: [],
      list: undefined,
      loading: true,
      pagination: {
        total: 0,
        page: 1,
        limit: 20
      }
    }
  },
  created() {
    this.onInit()
    EventBus.$on('SYNC_DEVICE', this.handleWhenSyncDeviceDone)
  },
  methods: {
    onInit() {
      this.search()
    },
    handleWhenSyncDeviceDone(data) {
      this.search()
    },
    async search() {
      this.loading = true
      this.addPaginationToSearchModel()
      const response = await filter(this.searchModel)
      this.showResult(response)
      this.loading = false
    },
    handleAdd() {
      this.formModel = {}
      this.isUpdateMode = false
      this.dialogFormVisible = true
    },
    handleUpdate(model) {
      this.isUpdateMode = true
      this.dialogFormVisible = true
      this.formModel = model
    },
    handleSelect(model) {
      this.selectedModels = model
    },
    async handleDelete() {
      await createConfirmBox(this.$i18n.t('device.multipleDeleteWarning'))
      this.continueDelete()
    },
    async continueDelete() {
      // TODO: need create api to delele all
      for (const model of this.selectedModels) {
        await doDelete(model.serial)
      }
      this.refreshList()
      showSuccessMessage()
    },
    refreshList() {
      this.search()
    },
    closeDialog() {
      this.dialogFormVisible = false
      this.refreshList()
    },
    showResult(response) {
      this.list = response.content
      this.pagination.total = response.totalElements
      this.pagination.page = response.pageable.pageNumber + 1
    },
    addPaginationToSearchModel() {
      this.searchModel.pageSize = this.pagination.limit
      this.searchModel.pageNumber = this.pagination.page
    }
  }
}
</script>
