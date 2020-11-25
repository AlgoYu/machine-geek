<template>
    <div>
        <div style="margin-top: 10px; margin-bottom: 10px">
            <el-row>
                <el-col :span="10">
                    <el-button
                        type="primary"
                        @click="addData"
                        >增加</el-button>
                </el-col>
                <el-col :span="4" :offset="10">
                    <el-input
                        @keyup.enter.native="getPage"
                        v-model="param.keyWord"
                        placeholder="按关键字搜索内容"
                        suffix-icon="el-icon-search"
                    ></el-input>
                </el-col>
            </el-row>
        </div>
        <el-table
            :data="table.data"
            style="width: 100%"
            v-loading="load"
        >
            <#list data as value>
            <el-table-column prop="${toHump(value.columnName)}" label="${value.columnComment}" align="center">
            </el-table-column>
            </#list>
            <el-table-column label="操作" align="center" min-width="150px">
                <template slot-scope="scope">
                    <el-button
                        type="info"
                        @click="edit(scope.row)"
                        >编辑</el-button
                    >
                    <el-button
                        type="danger"
                        @click="deleteData(scope.row)"
                        >删除</el-button
                    >
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            layout="prev, pager, next"
            :total="table.total"
            style="text-align: center"
            v-if="table.data.length > 0"
        >
        </el-pagination>
        <el-dialog
            title="${moduleName}"
            :visible.sync="formDialog"
            width="50%"
            :close-on-click-modal="false"
        >
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <#list data as value>
                    <el-form-item label="${value.columnComment}" prop="${toHump(value.columnName)}">
                        <el-input v-model="form.${toHump(value.columnName)}"></el-input>
                    </el-form-item>
                </#list>
                <el-form-item>
                    <el-button type="primary" @click="save">保存</el-button>
                    <el-button @click="formDialog = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
import {
    add,
    deleteById,
    modifyById,
    getById,
    list,
    paging
} from "../../api/${className}Api.js";
export default {
    data() {
        return {
            load: true,
            statu: "",
            form: {
                <#list data as value>
                ${toHump(value.columnName)}: "",
                </#list>
            },
            rules: {
                <#list data as value>
                ${toHump(value.columnName)}: [
                    {
                        required: true,
                        message: "${value.columnComment}不可为空",
                        trigger: "change",
                    },
                ],
                </#list>
            },
            formDialog: false,
            param: {
                page: 1,
                size: 10,
                keyWord: "",
            },
            table: {
                total: 0,
                data: [],
            },
        };
    },
    created() {
        this.init();
    },
    methods: {
        init() {
            this.getPage();
        },
        edit(row) {
            this.statu = "edit";
            this.form = row;
        },
        deleteData(row) {
            this.$confirm("确认删除这条数据吗?", "警告", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            })
                .then(() => {
                    deleteById(
                        {
                            id: row.id,
                        },
                        (result) => {
                            if (result.success) {
                                this.$message({
                                    message: "删除成功!",
                                    type: "success",
                                });
                                this.getPage();
                            }
                        }
                    );
                })
                .catch(() => {});
        },
        addData() {
            this.statu = "add";
            this.form = {
                <#list data as value>
                ${toHump(value.columnName)}: "",
                </#list>
            };
            this.formDialog = true;
        },
        getPage() {
            this.load = true;
            paging(this.param, (result) => {
                if (result.success) {
                    this.table.total = result.data.total;
                    this.table.data = result.data.records;
                }
                this.load = false;
            });
        },
        save() {
            this.$refs["form"].validate((valid) => {
                if (valid) {
                    switch (this.statu) {
                        case "add":
                            this.load = true;
                            add(this.form, (result) => {
                                if (result.success) {
                                    this.$message({
                                        message: "保存成功!",
                                        type: "success",
                                    });
                                    this.getPage();
                                } else {
                                    this.$message({
                                        message: "保存失败！",
                                        type: "warning",
                                    });
                                }
                                this.load = false;
                            });
                            break;
                        case "edit":
                            modifyById(this.form, (result) => {
                                if (result.success) {
                                    this.$message({
                                        message: "保存成功!",
                                        type: "success",
                                    });
                                    this.getPage();
                                } else {
                                    this.$message({
                                        message: "保存失败！",
                                        type: "warning",
                                    });
                                }
                                this.load = false;
                            });
                            break;
                    }
                    this.formDialog = false;
                } else {
                    this.$message({
                        message: "请完成表单",
                        type: "warning",
                    });
                    return false;
                }
            });
        },
    },
};
</script>

<style>
</style>
